package com.example.miha.criminalintent.presentation.mvp.crimeFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.miha.criminalintent.domain.crimeFragment.ICrimeFragmentInteractor;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.ui.utils.ImageUrlConverter;

@InjectViewState
public class CrimeFragmentPresenter extends MvpPresenter<CrimeFragmentView> {
    private ICrimeFragmentInteractor interactor;

    public CrimeFragmentPresenter(ICrimeFragmentInteractor crime) {
        this.interactor = crime;
    }

    public void init() {
        interactor.loadCrime(crime -> {
            if (!crime.getPhoto().isEmpty()) {
                String photo = ImageUrlConverter.getRealAddressFile(crime.getPhoto());
                getViewState().showPhoto(photo);
            }
            if (crime.getSuspect().getId() > 0) {
                getViewState().showSuspect(crime.getSuspect());
            }
            getViewState().showDate(crime.getDate());
            getViewState().showIsSolved(crime.getSolved());
            getViewState().showTitle(crime.getTitle());
            getViewState().showDate(crime.getDate());
            getViewState().showDetails(crime.getDetails());
        });
        interactor.isChangeableCrime(flag -> getViewState().showIsEnabled(flag));
    }

    public void clickChangeDate() {
        interactor.loadCrime(crime -> getViewState().showChangeDateCrime(crime.getDate()));
    }

    public void changeData(String date) {
        interactor.loadCrime(crime -> {
            crime.setDate(date);
            getViewState().showDate(date);
            getViewState().sendUpdateUiMessage(crime);
        });

    }

    public void clickOnImage() {
        interactor.loadCrime(crime -> getViewState().showBigImageInDialog(crime.getPhoto()));
    }

    public void onPause() {
        interactor.saveCrime();
    }

    public void changeTitle(String string) {
        interactor.loadCrime(crime -> {
            crime.setTitle(string);
            getViewState().sendUpdateUiMessage(crime);
        });
    }

    public void solved(Boolean isChecked) {
        interactor.loadCrime(crime -> {
            crime.setSolved(isChecked);
            getViewState().sendUpdateUiMessage(crime);
        });
    }

    public void takePicture() {
        getViewState().takePicture();
    }

    public void changePhoto(String fileName) {
        interactor.loadCrime(crime -> {
            crime.setPhoto(ImageUrlConverter.getRealAddressFile(fileName));
            getViewState().showPhoto(crime.getPhoto());
        });
    }

    public void changeDetails(String string) {
        interactor.loadCrime(crime -> crime.setDetails(string));
    }

    public void sendCrime() {
        interactor.loadCrime(crime -> getViewState().postCrime(crime));
    }

    public void setSuspectCrime() {
        getViewState().choiceSuspect();
    }

    public void setSuspectUser(User user) {
        interactor.loadCrime(crime -> {
            crime.setSuspect(user);
            getViewState().showSuspect(user);
        });
    }

    public void showComment() {
        interactor.loadCrime(crime -> getViewState().showComment(crime));
    }
}
