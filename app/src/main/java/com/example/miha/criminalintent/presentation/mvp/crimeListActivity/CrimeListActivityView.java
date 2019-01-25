package com.example.miha.criminalintent.presentation.mvp.crimeListActivity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.miha.criminalintent.domain.model.Crime;

public interface CrimeListActivityView extends MvpView {
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCrimeInNewActivity(Crime uuid);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showCrimeInFragment(Crime uuid);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAuth();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateCrimeList();
}
