package com.example.miha.criminalintent.presentation.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.fragment.CrimeFragment;

import java.util.ArrayList;
import java.util.List;

public class CrimeFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Crime> crimeList = new ArrayList<>();

    public CrimeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setCrimeList(List<Crime> crimeList) {
        this.crimeList = crimeList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return CrimeFragment.newInstance(crimeList.get(i));
    }

    @Override
    public int getCount() {
        return crimeList.size();
    }
}
