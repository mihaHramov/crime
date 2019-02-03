package com.example.miha.criminalintent.presentation.ui.activity;


import android.support.v4.app.Fragment;

import com.example.miha.criminalintent.presentation.ui.fragment.UserListFragment;

public class UserListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new UserListFragment();
    }
}
