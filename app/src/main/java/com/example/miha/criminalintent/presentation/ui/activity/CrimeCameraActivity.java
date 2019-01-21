package com.example.miha.criminalintent.presentation.ui.activity;

import android.support.v4.app.Fragment;

import com.example.miha.criminalintent.presentation.ui.fragment.CrimeCameraFragment;


public class CrimeCameraActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
