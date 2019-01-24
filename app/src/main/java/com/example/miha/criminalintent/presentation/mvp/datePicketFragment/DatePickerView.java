package com.example.miha.criminalintent.presentation.mvp.datePicketFragment;

import com.arellomobile.mvp.MvpView;

import java.util.Date;

public interface DatePickerView extends MvpView {
    void initDateView(Date date);

    void close();

    void changeDateCrime(String datetime);
}
