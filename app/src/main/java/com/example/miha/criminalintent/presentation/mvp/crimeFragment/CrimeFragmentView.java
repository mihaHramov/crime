package com.example.miha.criminalintent.presentation.mvp.crimeFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.Crime;

public interface CrimeFragmentView extends MvpView {
    void showCrime(Crime crime);

}
