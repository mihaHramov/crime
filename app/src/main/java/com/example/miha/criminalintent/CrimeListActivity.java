package com.example.miha.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by miha on 26.07.2016.
 */
public class CrimeListActivity extends SingleFragmentActivity { // активити списка
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
}
