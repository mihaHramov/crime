package com.example.miha.criminalintent.di.application;

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
import com.example.miha.criminalintent.di.global.ApiModule;
import com.example.miha.criminalintent.di.global.RepositoryModule;
import com.example.miha.criminalintent.di.global.SchedulerModule;
import com.example.miha.criminalintent.di.global.UtilModule;
import com.example.miha.criminalintent.di.userListActivity.UserListActivityComponent;

import dagger.Component;

@Component(modules = {ContextModule.class,
        SchedulerModule.class,
        ApiModule.class,
        RepositoryModule.class, UtilModule.class})
public interface ApplicationComponent {
    CrimeListFragmentComponnent getCrimeListFragmentComponnent();

    AuthComponent getAuthComponent();

    CrimeListActivityComponent getCrimeListActivityComponent();

    CrimeFragmentComponent getCrimeFragmentComponent(CrimeFragmentModule crimeFragmentModule);

    CrimePagerActivityComponent getPagerActivityComponent(CrimePagerActivityModule module);

    DatePickerComponent getDatePickerComponent(DatePickerModule module);

    UserListActivityComponent getUserListComponent();

    CommentListFragmentComponent getCommentsComponent(CommentsListFragmentModule module);

    CommentsListActivityComponent getCommentsActivityComponent(CommentsListActivityModule module);

    CommentsCreateFragmentComponent getComponentCommentsCreateFragment(CommentsCreateFragmentModule module);
}
