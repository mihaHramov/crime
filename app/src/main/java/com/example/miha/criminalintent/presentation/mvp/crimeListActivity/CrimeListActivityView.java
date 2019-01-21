package com.example.miha.criminalintent.presentation.mvp.crimeListActivity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.miha.criminalintent.domain.model.Crime;

public interface CrimeListActivityView extends MvpView {
    void showCrimeInNewActivity(Crime uuid);

    void showCrimeInFragment(Crime uuid);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAuth();
}
