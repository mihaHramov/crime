package com.example.miha.criminalintent.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.miha.criminalintent.R;

public abstract class SingleFragmentActivity extends MvpAppCompatActivity { // хост активность для фрагмента
    protected abstract Fragment createFragment();
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        setSupportActionBar(findViewById(R.id.toolbar));
        FragmentManager fm = getSupportFragmentManager();//менеджер из библиотеки потдержки
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
