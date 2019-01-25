package com.example.miha.criminalintent.presentation.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeListActivity.CrimeListActivityView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.dialog.AuthFragmentDialog;
import com.example.miha.criminalintent.presentation.ui.fragment.CrimeFragment;
import com.example.miha.criminalintent.presentation.ui.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeListFragment.Callbacks, CrimeListActivityView { // активити списка

    @InjectPresenter
    CrimeListActivityPresenter presenter;

    @ProvidePresenter
    public CrimeListActivityPresenter providePresenter() {
        presenter = ApplicationCrime.getCrimeListActivityComponent().getPresenter();
        presenter.init(findViewById(R.id.detailFragmentContainer) != null);
        return presenter;
    }

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_crime_list, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_crime:
                presenter.createNewCrime();
                return true;
            case R.id.menu_item_login_user:
                presenter.showAuth();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        presenter.choiceItemCrime(crime);
    }

    @Override
    public void showCrimeInNewActivity(Crime crime) {
        CrimePagerActivity.startActivity(this, crime);
    }

    @Override
    public void showCrimeInFragment(Crime uuid) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detailFragmentContainer, CrimeFragment.newInstance(uuid))
                .commit();
    }


    @Override
    public void showAuth() {
        new AuthFragmentDialog().show(getSupportFragmentManager(), "");
    }

    @Override
    public void updateCrimeList() {
        FragmentManager fm = getSupportFragmentManager();
        CrimeListFragment listFragment = (CrimeListFragment)
                fm.findFragmentById(R.id.fragmentContainer);
        if (listFragment != null) {
            listFragment.onCreateCrime();
        }
    }
}
