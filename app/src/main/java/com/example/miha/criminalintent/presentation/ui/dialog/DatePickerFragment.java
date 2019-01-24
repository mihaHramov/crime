package com.example.miha.criminalintent.presentation.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.events.BusProvider;
import com.example.miha.criminalintent.domain.events.OnChangeDateCrime;
import com.example.miha.criminalintent.presentation.mvp.datePicketFragment.DatePickerFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.datePicketFragment.DatePickerView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatePickerFragment extends MvpAppCompatDialogFragment implements DatePickerView {
    private static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
    @BindView(R.id.dialog_date_datePicker)
    DatePicker datePicker;

    @InjectPresenter
    DatePickerFragmentPresenter presenter;

    @ProvidePresenter
    DatePickerFragmentPresenter providePresenter() {
        presenter = ApplicationCrime.getDatePickerComponent(getArguments().getString(EXTRA_DATE)).getPresenter();
        presenter.init();
        return presenter;
    }

    @OnClick(R.id.cancel)
    void clickOnCancel() {
        presenter.clickOnCancel();
    }

    @OnClick(R.id.ok)
    void clickOk() {
        presenter.clickOk();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_data, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void close() {
        dismiss();
    }

    public static DatePickerFragment newInstance(String date) {
        Bundle args = new Bundle();
        args.putString(EXTRA_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initDateView(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, (view, year1, monthOfYear, dayOfMonth) -> {
            GregorianCalendar m = new GregorianCalendar(year1, monthOfYear, dayOfMonth);
            m.setTime(date);
            m.set(Calendar.YEAR, year1);
            m.set(Calendar.MONTH, monthOfYear);
            m.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            presenter.changeDate(m.getTime());
        });
    }

    @Override
    public void changeDateCrime(String datetime) {
        BusProvider.getInstance().post(new OnChangeDateCrime(datetime));
        dismiss();
    }
}
