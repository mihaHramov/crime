package com.example.miha.criminalintent.presentation.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeFragment.CrimeFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CrimeFragment extends MvpAppCompatFragment implements CrimeFragmentView {
    private static final String EXTRA_CRIME = CrimeFragment.class.getCanonicalName();

    public static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 1;
    private static final int REQUEST_CONTACT = 2;
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

    public static CrimeFragment newInstance(Crime crime) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME, crime);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void showCrime(Crime crime) {
        mTitleField.setText(crime.getTitle());
        mSolvedCheckBox.setChecked(crime.getSolved());
        mDateButton.setText(crime.getDate());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
        });

        mPhotoButton.setOnClickListener(v1 -> {
            //   Intent i = new Intent(getActivity(), CrimeCameraActivity.class);
            // startActivityForResult(i, REQUEST_PHOTO);
        });
        mDateButton.setOnClickListener(v15 -> {
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
//            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
//            dialog.show(fm, DIALOG_DATE);
        });


        mPhotoView.setOnClickListener(v12 -> {
            String p = crime.getPhoto();
            if (!p.isEmpty()) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                String path = getActivity().getFileStreamPath(p).getAbsolutePath();
//              ImageFragment.newInstance(path)
//                      .show(fm, DIALOG_IMAGE);
            }
        });
        PackageManager pm = getActivity().getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            mPhotoButton.setEnabled(false);
        }


        reportButton.setOnClickListener(v13 -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            //  i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
            i.putExtra(Intent.EXTRA_SUBJECT,
                    getString(R.string.crime_report_subject));
            i = Intent.createChooser(i, getString(R.string.send_report));
            startActivity(i);
        });


        mSuspectButton.setOnClickListener(v14 -> {
            Intent i = new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i, REQUEST_CONTACT);
        });
        if (crime.getSuspect() != null) {
            mSuspectButton.setText(crime.getSuspect().getName());
        }
    }

    //
//    @Override
//    public void onPause() {
//        super.onPause();
//        //CrimeLab.get(getActivity()).saveCrimes();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        showPhoto();
//    }
//
//
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
        ButterKnife.bind(this, v);
        return v;
    }
//
//    private void showPhoto() {
//        // Назначение изображения, полученного на основе фотографии
//        String p = mCrime.getPhoto();
//        BitmapDrawable b = null;
//        if (p != null) {
//            String path = getActivity()
//                    .getFileStreamPath(p).getAbsolutePath();
//            b = PictureUtils.getScaledDrawable(getActivity(), path);
//        }
//        mPhotoView.setImageDrawable(b);
//    }
//
//
//    private String getCrimeReport() {
//        String solvedString = mCrime.getSolved() ? getString(R.string.crime_report_solved) : getString(R.string.crime_report_unsolved);
//        String dateFormat = "EEE, MMM dd";
//        //String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();
//        String suspect = mCrime.getSuspect() == null ? getString(R.string.crime_report_no_suspect) : getString(R.string.crime_report_suspect, mCrime.getSuspect());
//        return getString(R.string.crime_report, mCrime.getTitle(), mCrime.getDate(), solvedString, suspect);
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        PictureUtils.cleanImageView(mPhotoView);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) return;
//        if (requestCode == REQUEST_DATE) {//проверяяем с какого фрагмента пришел ответ
//            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
//         //   mCrime.setDate(date);
//            //  mDateButton.setText(mCrime.getDate().toString());
//            mCallbacks.onCrimeUpdated(mCrime);
//            updateDate();
//        } else if (requestCode == REQUEST_PHOTO) {
//            String filename = data.getStringExtra(CrimeCameraFragment.EXTRA_PHOTO_FILENAME);
//            if (filename != null) {
//                mCrime.setPhoto(filename);
//                showPhoto();
//                mCallbacks.onCrimeUpdated(mCrime);
//            }
//        } else if (requestCode == REQUEST_CONTACT) {
//            Uri contactUri = data.getData();
//            // Определение полей, значения которых должны быть
//            // возвращены запросом.
//            String[] queryFields = new String[]{
//                    ContactsContract.Contacts.DISPLAY_NAME
//            };
//            // Выполнение запроса - contactUri здесь выполняет функции
//            // условия "where"
//            Cursor c = getActivity().getContentResolver()
//                    .query(contactUri, queryFields, null, null, null);
//            // Проверка получения результатов
//            if (c.getCount() == 0) {
//                c.close();
//                return;
//            }
//            // Извлечение первого столбца данных - имени подозреваемого.
//            c.moveToFirst();
//            String suspect = c.getString(0);
//           // mCrime.setSuspect(suspect);
//            mSuspectButton.setText(suspect);
//            mCallbacks.onCrimeUpdated(mCrime);
//            c.close();
//        }
//    }
}
