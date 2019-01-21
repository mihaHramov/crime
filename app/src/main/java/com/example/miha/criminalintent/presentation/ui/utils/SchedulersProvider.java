package com.example.miha.criminalintent.presentation.ui.utils;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulersProvider implements ISchedulersProvider {

    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler computation() {
        return Schedulers.computation();
    }

    public Scheduler io() {
        return Schedulers.io();
    }

    public Scheduler newThread() {
        return Schedulers.newThread();
    }

    public Scheduler trampoline() {
        return Schedulers.trampoline();
    }
}