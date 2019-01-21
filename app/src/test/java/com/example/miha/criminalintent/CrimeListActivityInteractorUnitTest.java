package com.example.miha.criminalintent;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.crimeListActivity.CrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.crimeListActivity.ICrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.ui.utils.ISchedulersProvider;
import com.example.miha.criminalintent.presentation.ui.utils.SchedulersProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CrimeListActivityInteractorUnitTest {
    //   private CrimeListActivityView$$State view;
    private ICrimeListActivityInteractor interactor;
    private IRepositoryOfCrime repositoryOfCrime;
    private ISchedulersProvider schedulersProvider;
    private ICrimeListActivityInteractor.Callback callback;

    @Before
    public void before() {
        //     view = mock(CrimeListActivityView$$State.class);
        repositoryOfCrime = mock(IRepositoryOfCrime.class);
        schedulersProvider = mock(SchedulersProvider.class);
        callback = mock(ICrimeListActivityInteractor.Callback.class);
        //state = mock(ViewNewsListFragment$$State.class);

    }

    @Test
    public void create_crime_isCorrect() {
        when(repositoryOfCrime.create()).then((Answer<Observable<Crime>>) invocation -> Observable.just(new Crime()));
        when(schedulersProvider.newThread()).thenReturn(Schedulers.immediate());
        when(schedulersProvider.ui()).thenReturn(Schedulers.immediate());
        interactor = new CrimeListActivityInteractor(repositoryOfCrime, schedulersProvider);
        interactor.createNewCrime(callback);
        verify(callback).onSuccess(any(Crime.class));
    }

}