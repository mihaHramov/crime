package com.example.miha.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by miha on 07.12.2016.
 */
public class CrimeCameraActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeCameraFragment();
    }
}
