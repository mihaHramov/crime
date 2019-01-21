package com.example.miha.criminalintent.di.auth;

import com.example.miha.criminalintent.presentation.mvp.authUserFragment.AuthUserFragmentPresenter;

import dagger.Subcomponent;

@Subcomponent(modules = AuthModule.class)
public interface AuthComponent {
    AuthUserFragmentPresenter getPresenter();
}
