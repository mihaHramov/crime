package com.example.miha.criminalintent;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.IRepositoryOfCrime;
import com.example.miha.criminalintent.domain.crimeListActivity.CrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.crimeListActivity.ICrimeListActivityInteractor;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityView$$State;
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
public class CrimeListActivityPresenterUnitTest {
    private CrimeListActivityView$$State view;
    private CrimeListActivityPresenter presenter;

    @Before
    public void before() {
        view = mock(CrimeListActivityView$$State.class);
        IRepositoryOfCrime repositoryOfCrime = mock(IRepositoryOfCrime.class);
        ISchedulersProvider schedulersProvider = mock(SchedulersProvider.class);
        ICrimeListActivityInteractor interactor = new CrimeListActivityInteractor(repositoryOfCrime, schedulersProvider);
        when(repositoryOfCrime.create()).then((Answer<Observable<Crime>>) invocation -> Observable.just(new Crime()));
        when(schedulersProvider.newThread()).thenReturn(Schedulers.immediate());
        when(schedulersProvider.ui()).thenReturn(Schedulers.immediate());
        presenter = new CrimeListActivityPresenter(interactor);
        presenter.setViewState(view);

    }

    @Test
    public void create_new_crime_on_twoPanelInterface_isCorrect() {
        presenter.init(true);
        presenter.createNewCrime();
        verify(view).showCrimeInFragment(any(Crime.class));
    }

    @Test
    public void create_new_crime_on_onePanelInterface_isCorrect() {
        presenter.init(false);
        presenter.createNewCrime();
        verify(view).showCrimeInNewActivity(any(Crime.class));
    }
    @Test
    public void show_auth_isCorrect() {
        presenter.showAuth();
        verify(view).showAuth();
    }

}