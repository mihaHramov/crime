package com.example.miha.criminalintent.presentation.mvp.crimeFragment;

import com.arellomobile.mvp.MvpView;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.User;

public interface CrimeFragmentView extends MvpView {
    void showChangeDateCrime(String date);

    void showBigImageInDialog(String photo);

    void showPhoto(String photo);

    void showSuspect(User user);

    void showIsSolved(Boolean isSolved);

    void showTitle(String title);

    void showDate(String date);

    void sendUpdateUiMessage(Crime crime);

    void takePicture();

    void showIsEnabled(Boolean bool);

    void showDetails(String details);

    void postCrime(Crime crime);
}
