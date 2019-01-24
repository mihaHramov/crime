package com.example.miha.criminalintent.presentation.ui.fragment;

import android.content.pm.PackageManager;
import android.os.Bundle;
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

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.events.BusProvider;
import com.example.miha.criminalintent.domain.events.OnChangeDateCrime;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.dialog.DatePickerFragment;
import com.example.miha.criminalintent.presentation.ui.dialog.ImageFragment;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CrimeFragment extends MvpAppCompatFragment implements CrimeFragmentView {
    private static final String EXTRA_CRIME = CrimeFragment.class.getCanonicalName();
    private FragmentManager fm;
    // private static final int REQUEST_PHOTO = 1;
     private static final String DIALOG_IMAGE = "image";

    @BindView(R.id.crime_title)
    EditText mTitleField;
    @BindView(R.id.crime_date)
    Button mDateButton;

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
        mPhotoView.setOnClickListener(v12 -> presenter.clickOnImage());
    }

    private TextWatcher titleWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

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

    @Subscribe
    public void changeDateCrime(OnChangeDateCrime onChangeDateCrime) {
        presenter.changeData(onChangeDateCrime.getDate());
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
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
    }

    @Override
    public void showDate(String date) {
        mDateButton.setText(date);
    }

    @Override
    public void showChangeDateCrime(String date) {
        DatePickerFragment.newInstance(date).show(fm, "");
    }

    //
//    @Override
//    public void onPause() {
//        super.onPause();
//        //CrimeLab.get(getActivity()).saveCrimes();
//    }


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
        mPhotoButton.setOnClickListener(v1 -> {
            //   Intent i = new Intent(getActivity(), CrimeCameraActivity.class);
            // startActivityForResult(i, REQUEST_PHOTO);
        });
        PackageManager pm = getActivity().getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            mPhotoButton.setEnabled(false);
        }


        reportButton.setOnClickListener(v13 -> {
        });


        mSuspectButton.setOnClickListener(v14 -> {
        });
        return v;
    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) return;
//        if (requestCode == REQUEST_PHOTO) {
//            String filename = data.getStringExtra(CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
//            if (filename != null) {
//                mCrime.setPhoto(filename);
//                showPhoto();
//                mCallbacks.onCrimeUpdated(mCrime);
//            }
//        }
}
