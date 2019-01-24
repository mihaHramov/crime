package com.example.miha.criminalintent.presentation.mvp.datePicketFragment;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@InjectViewState
public class DatePickerFragmentPresenter extends MvpPresenter<DatePickerView> {
    private Date mDate;
    private String data;
    private SimpleDateFormat format;
    private static final String dateFormat = "yyyy-MM-dd";

    public DatePickerFragmentPresenter(String data) {
        this.data = data;
        format = new SimpleDateFormat(dateFormat);
        try {
            mDate = format.parse(data);
        } catch (ParseException e) {
            mDate = new Date();
        }
    }

    public void init() {
        getViewState().initDateView(mDate);
    }

    public void changeDate(Date time) {
        mDate = time;
    }

    public void clickOnCancel() {
        getViewState().close();
    }

    public void clickOk() {
        String datetime = format.format(mDate);
        getViewState().changeDateCrime(datetime);
    }
}
