package com.example.miha.criminalintent.di.datePickerFragment;

import com.example.miha.criminalintent.presentation.mvp.datePicketFragment.DatePickerFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = DatePickerModule.class)
public interface DatePickerComponent {
    DatePickerFragmentPresenter getPresenter();
}
