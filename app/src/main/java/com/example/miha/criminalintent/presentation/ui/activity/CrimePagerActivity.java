package com.example.miha.criminalintent.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimePagerActivity.CrimePagerActivityPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimePagerActivity.CrimePagerActivityView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.adapter.CrimeFragmentPagerAdapter;

import java.util.List;

public class CrimePagerActivity extends MvpAppCompatActivity implements CrimePagerActivityView {
    private ViewPager mViewPager;
    private static String EXTRA_CRIME = CrimePagerActivity.class.getCanonicalName();
    private CrimeFragmentPagerAdapter adapter;


    public static void startActivity(Context context, Crime crime) {
        Intent i = new Intent(context, CrimePagerActivity.class);
        i.putExtra(EXTRA_CRIME, crime);
        context.startActivity(i);
    }


    @InjectPresenter
    CrimePagerActivityPresenter presenter;

    @ProvidePresenter
    CrimePagerActivityPresenter providePresenter() {
        Crime crime = (Crime) getIntent().getSerializableExtra(EXTRA_CRIME);
        presenter = ApplicationCrime.getCrimePagerActivityComponent(crime).getPresenter();
        presenter.init();
        return presenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);
        adapter = new CrimeFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void showCurrentPosition(Integer integer) {
        mViewPager.setCurrentItem(integer);
    }

    @Override
    public void showCrimes(List<Crime> crimes) {
        adapter.setCrimeList(crimes);
    }
}
