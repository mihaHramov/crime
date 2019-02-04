package com.example.miha.criminalintent.presentation.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.events.BusProvider;
import com.example.miha.criminalintent.domain.events.OnChangeCrimeEvent;
import com.example.miha.criminalintent.domain.events.OnSendCrimeToServer;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.activity.UserListActivity;
import com.example.miha.criminalintent.presentation.ui.dialog.DatePickerFragment;
import com.example.miha.criminalintent.presentation.ui.dialog.ImageFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CrimeFragment extends MvpAppCompatFragment implements CrimeFragmentView,
        DatePickerFragment.OnChangeDate {
    private static final String EXTRA_CRIME = CrimeFragment.class.getCanonicalName();
    private FragmentManager fm;
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_DATE = 2;
    private static final int REQUEST_SUSPECT = 3;
    private static final String DIALOG_IMAGE = "image";
    private PackageManager pm;

    @BindView(R.id.crime_title)
    EditText mTitleField;
    @BindView(R.id.crime_date)
    Button mDateButton;

    @BindView(R.id.details)
    EditText mDetails;
    @BindView(R.id.crime_solved)
    CheckBox mSolvedCheckBox;

    @BindView(R.id.crime_suspectButton)
    Button mSuspectButton;

    @BindView(R.id.crime_imageButton)
    ImageButton mPhotoButton;

    @BindView(R.id.crime_imageView)
    ImageView mPhotoView;


    @BindView(R.id.crime_reportButton)
    Button reportButton;

    @InjectPresenter
    CrimeFragmentPresenter presenter;

    @ProvidePresenter
    CrimeFragmentPresenter providePresenter() {
        Crime crime = (Crime) getArguments().getSerializable(EXTRA_CRIME);
        presenter = ApplicationCrime.getCrimeFragmentComponent(crime).getPresenter();
        presenter.init();
        return presenter;
    }

    @Override
    public void showPhoto(String photo) {
        Picasso.get().load(photo)
                .placeholder(R.drawable.ic_action_account_circle)
                .error(R.drawable.ic_action_account_circle)
                .into(mPhotoView);
        mPhotoView.setOnClickListener(v -> presenter.clickOnImage());
    }

    private TextWatcher detailsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            presenter.changeDetails(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    private TextWatcher titleWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.changeTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void sendUpdateUiMessage(Crime crime) {
        BusProvider.getInstance().post(new OnChangeCrimeEvent(crime));
    }

    public static CrimeFragment newInstance(Crime crime) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME, crime);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
        fm = getActivity().getSupportFragmentManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }


    @Override
    public void changeDate(String date) {
        presenter.changeData(date);
    }

    @Override
    public void showSuspect(User user) {
        mSuspectButton.setText(user.getName());
    }

    @Override
    public void showTitle(String title) {
        mTitleField.removeTextChangedListener(titleWatcher);
        mTitleField.setText(title);
        mTitleField.addTextChangedListener(titleWatcher);
    }

    @Override
    public void showIsSolved(Boolean isSolved) {
        mSolvedCheckBox.setChecked(isSolved);
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> presenter.solved(isChecked));
    }

    @Override
    public void showDate(String date) {
        mDateButton.setText(date);
    }

    @Override
    public void showChangeDateCrime(String date) {
        DatePickerFragment dialog = DatePickerFragment.newInstance(date);
        dialog.setTargetFragment(this, REQUEST_DATE);
        dialog.show(fm, "");
    }


    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }


    @Override
    public void showBigImageInDialog(String photo) {
        ImageFragment.newInstance(photo).show(fm, DIALOG_IMAGE);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
        ButterKnife.bind(this, v);
        mDateButton.setOnClickListener(v15 -> presenter.clickChangeDate());
        pm = getActivity().getPackageManager();

        mPhotoButton.setOnClickListener(v1 -> presenter.takePicture());

        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            mPhotoButton.setEnabled(false);
        }


        reportButton.setOnClickListener(v13 -> presenter.sendCrime());


        mSuspectButton.setOnClickListener(v14 -> presenter.setSuspectCrime());
        return v;
    }

    @Override
    public void choiceSuspect() {
        Intent i = new Intent(getActivity(),UserListActivity.class);
        startActivityForResult(i,REQUEST_SUSPECT);
    }

    @Override
    public void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(pm) != null) {
            File mPhotoFile = null;
            try {
                mPhotoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(getActivity(), getString(R.string.file_error), Toast.LENGTH_LONG).show();
            }
            if (mPhotoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
                presenter.createPhoto(mPhotoFile.getAbsolutePath());
                startActivityForResult(takePictureIntent, REQUEST_PHOTO);
            }
        }
    }

    @Override
    public void showIsEnabled(Boolean bool) {
        mDateButton.setEnabled(bool);
        mPhotoButton.setEnabled(bool);
        mSolvedCheckBox.setEnabled(bool);
        mDetails.setEnabled(bool);
        mTitleField.setEnabled(bool);
    }

    @Override
    public void showDetails(String details) {
        mDetails.removeTextChangedListener(detailsWatcher);
        mDetails.setText(details);
        mDetails.addTextChangedListener(detailsWatcher);
    }

    @Override
    public void postCrime(Crime crime) {
        BusProvider.getInstance().post(new OnSendCrimeToServer(crime));
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        //проверяяем от куда пришел ответ
        if (requestCode == REQUEST_PHOTO) {
            presenter.changePhoto();
        }
        if (requestCode == REQUEST_DATE) {
            String date = data.getStringExtra(DatePickerFragment.EXTRA_DATE);
            presenter.changeData(date);
        }
        if(requestCode == REQUEST_SUSPECT){
            User user = (User) data.getSerializableExtra(UserListFragment.SUSPECT_USER);
            presenter.setSuspectUser(user);
        }
    }
}