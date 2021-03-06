package com.example.miha.criminalintent.presentation.mvp.crimePagerActivity;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.ItemCrime;

import java.util.List;

public interface CrimePagerActivityView extends MvpView {
    void showCurrentPosition(Integer integer);

    void showCrimes(List<ItemCrime> crimes);

    void showResultOfSendCrime(String string);
}
