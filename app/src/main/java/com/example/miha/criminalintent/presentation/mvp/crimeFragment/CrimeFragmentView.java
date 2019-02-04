package com.example.miha.criminalintent.presentation.mvp.crimeFragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;

public interface CrimeFragmentView extends MvpView {
    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showChangeDateCrime(String date);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showBigImageInDialog(String photo);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showPhoto(String photo);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void showSuspect(User user);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showIsSolved(Boolean isSolved);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void sendUpdateUiMessage(Crime crime);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void takePicture();

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showIsEnabled(Boolean bool);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void postCrime(Crime crime);

    @StateStrategyType(value = OneExecutionStateStrategy.class)
    void choiceSuspect();

    void showDetails(String details);

    void showTitle(String title);

    void showDate(String date);
}
