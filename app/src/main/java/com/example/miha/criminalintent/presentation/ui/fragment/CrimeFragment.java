package com.example.miha.criminalintent.presentation.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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

import com.example.miha.criminalintent.PictureUtils;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.activity.CrimeCameraActivity;
import com.example.miha.criminalintent.presentation.ui.dialog.DatePickerFragment;
import com.example.miha.criminalintent.presentation.ui.dialog.ImageFragment;

import java.util.Date;
import java.util.UUID;


public class CrimeFragment extends Fragment {
//    public static final String EXTRA_CRIME_ID = "com.bignerdranch.android.criminalintent.crime_id";
//    public static final String DIALOG_DATE = "date";
//    private static final int REQUEST_DATE = 0;
//    private static final int REQUEST_PHOTO = 1;
//    private static final int REQUEST_CONTACT = 2;
//
//    private Crime mCrime;
//    private EditText mTitleField;
//    private Button mDateButton;
//    private CheckBox mSolvedCheckBox;
//    private Button mSuspectButton;
//    private ImageButton mPhotoButton;
//    private ImageView mPhotoView;
//    private static final String DIALOG_IMAGE = "image";
//    private Callbacks mCallbacks;
//
//    public interface Callbacks {
//        void onCrimeUpdated(Crime crime);
//    }
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mCallbacks = (Callbacks) activity;
//    }
//
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mCallbacks = null;
//    }
//
//    public static CrimeFragment newInstance(UUID crimeId) {
//        Bundle args = new Bundle();
//        args.putSerializable(EXTRA_CRIME_ID, crimeId);
//        CrimeFragment fragment = new CrimeFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        UUID crimeId = (UUID) getArguments().getSerializable(EXTRA_CRIME_ID);//забрал из аргументов id
//       // mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
//
//    }
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
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragment_crime, parent, false);
//        mTitleField = v.findViewById(R.id.crime_title);
//        mTitleField.setText(mCrime.getTitle());
//
//
//        mTitleField.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mCrime.setTitle(s.toString());
//                mCallbacks.onCrimeUpdated(mCrime);
//                getActivity().setTitle(mCrime.getTitle());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        mDateButton = v.findViewById(R.id.crime_date);
//        updateDate();
//        mDateButton.setOnClickListener(v15 -> {
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
//            dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
//            dialog.show(fm, DIALOG_DATE);
//
//        });
//
//        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
//        mSolvedCheckBox.setChecked(mCrime.getSolved());
//        mSolvedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            mCrime.setSolved(isChecked);
//            mCallbacks.onCrimeUpdated(mCrime);
//        });
//
//        mPhotoButton = v.findViewById(R.id.crime_imageButton);
//        mPhotoButton.setOnClickListener(v1 -> {
//            Intent i = new Intent(getActivity(), CrimeCameraActivity.class);
//            startActivityForResult(i, REQUEST_PHOTO);
//        });
//        mPhotoView = v.findViewById(R.id.crime_imageView);
//        mPhotoView.setOnClickListener(v12 -> {
//            String p = mCrime.getPhoto();
//            if (p == null)
//                return;
//            FragmentManager fm = getActivity().getSupportFragmentManager();
//            String path = getActivity().getFileStreamPath(p).getAbsolutePath();
//            ImageFragment.newInstance(path)
//                    .show(fm, DIALOG_IMAGE);
//
//        });
//        PackageManager pm = getActivity().getPackageManager();
//        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) && !pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
//            mPhotoButton.setEnabled(false);
//        }
//
//        Button reportButton = v.findViewById(R.id.crime_reportButton);
//        reportButton.setOnClickListener(v13 -> {
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("text/plain");
//            i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
//            i.putExtra(Intent.EXTRA_SUBJECT,
//                    getString(R.string.crime_report_subject));
//            i = Intent.createChooser(i, getString(R.string.send_report));
//            startActivity(i);
//        });
//
//        mSuspectButton = v.findViewById(R.id.crime_suspectButton);
//        mSuspectButton.setOnClickListener(v14 -> {
//            Intent i = new Intent(Intent.ACTION_PICK,
//                    ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(i, REQUEST_CONTACT);
//        });
//        if (mCrime.getSuspect() != null) {
//            mSuspectButton.setText(mCrime.getSuspect().getName());
//        }
//
//
//        return v;
//    }
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
//    public void updateDate() {
//        mDateButton.setText(mCrime.getDate().toString());
//    }
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
