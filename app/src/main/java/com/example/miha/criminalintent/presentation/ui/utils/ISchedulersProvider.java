package com.example.miha.criminalintent.presentation.ui.utils;

import rx.Scheduler;

public interface ISchedulersProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
    Scheduler newThread();
    Scheduler trampoline();
}
