package com.example.miha.criminalintent.presentation.ui;

import com.crashlytics.android.Crashlytics;
import com.example.miha.criminalintent.di.application.ApplicationComponent;
import com.example.miha.criminalintent.di.application.ContextModule;
import com.example.miha.criminalintent.di.application.DaggerApplicationComponent;
import com.example.miha.criminalintent.di.auth.AuthComponent;
import com.example.miha.criminalintent.di.commentListFragment.CommentListFragmentComponent;
import com.example.miha.criminalintent.di.commentListFragment.CommentsListFragmentModule;
import com.example.miha.criminalintent.di.commentsCreateFragment.CommentsCreateFragmentComponent;
import com.example.miha.criminalintent.di.commentsCreateFragment.CommentsCreateFragmentModule;
import com.example.miha.criminalintent.di.commentsListActivity.CommentsListActivityComponent;
import com.example.miha.criminalintent.di.commentsListActivity.CommentsListActivityModule;
import com.example.miha.criminalintent.di.crimeFragment.CrimeFragmentComponent;
import com.example.miha.criminalintent.di.crimeFragment.CrimeFragmentModule;
import com.example.miha.criminalintent.di.crimeListActivity.CrimeListActivityComponent;
import com.example.miha.criminalintent.di.crimeListFragment.CrimeListFragmentComponnent;
import com.example.miha.criminalintent.di.crimePagerActivity.CrimePagerActivityComponent;
import com.example.miha.criminalintent.di.crimePagerActivity.CrimePagerActivityModule;
import com.example.miha.criminalintent.di.datePickerFragment.DatePickerComponent;
import com.example.miha.criminalintent.di.datePickerFragment.DatePickerModule;
import com.example.miha.criminalintent.di.userListActivity.UserListActivityComponent;
import com.example.miha.criminalintent.domain.model.Crime;
import io.fabric.sdk.android.Fabric;

public class ApplicationCrime extends android.app.Application {
    static ApplicationComponent component;

    public static AuthComponent getAuthComponent() {
        return component.getAuthComponent();
    }

    public static DatePickerComponent getDatePickerComponent(String string) {
        return component.getDatePickerComponent(new DatePickerModule(string));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static CrimeListFragmentComponnent getCrimeListFragmentComponent() {
        return component.getCrimeListFragmentComponnent();
    }

    public static CrimeListActivityComponent getCrimeListActivityComponent() {
        return component.getCrimeListActivityComponent();
    }

    public static CrimeFragmentComponent getCrimeFragmentComponent(Crime crime) {
        return component.getCrimeFragmentComponent(new CrimeFragmentModule(crime));
    }

    public static CrimePagerActivityComponent getCrimePagerActivityComponent(Crime crime) {
        return component.getPagerActivityComponent(new CrimePagerActivityModule(crime));
    }

    public static UserListActivityComponent getUserListActivityComponent() {
        return component.getUserListComponent();
    }

    public static CommentListFragmentComponent getCommentsComponent(Crime crime) {
        return component.getCommentsComponent(new CommentsListFragmentModule(crime));
    }

    public static CommentsListActivityComponent getCommentsListActivityComponent(Crime crime) {
        return component.getCommentsActivityComponent(new CommentsListActivityModule(crime));
    }

    public static CommentsCreateFragmentComponent getCommentsCreateFragmentComponent(Crime crime) {
        return component.getComponentCommentsCreateFragment(new CommentsCreateFragmentModule(crime));
    }
}
