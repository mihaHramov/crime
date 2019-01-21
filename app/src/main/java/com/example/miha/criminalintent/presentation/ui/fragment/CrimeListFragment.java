package com.example.miha.criminalintent.presentation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.crimeListFragment.CrimeListFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.crimeListFragment.CrimeListFragmentsView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.adapter.CrimeRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CrimeListFragment extends MvpAppCompatFragment implements
        CrimeListFragmentsView {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Callbacks mCallbacks;
    private CrimeRecyclerViewAdapter adapter;

    public interface Callbacks {
        void onCrimeSelected(Crime crime);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callbacks) {
            mCallbacks = (Callbacks) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @InjectPresenter
    CrimeListFragmentPresenter presenter;

    @ProvidePresenter
    CrimeListFragmentPresenter providePresenter() {
        return ApplicationCrime.getCrimeListFragmentComponent().getPresenter().init();
    }

    @Override
    public void showCrimes(List<Crime> crimes) {
        adapter.setCrimes(crimes);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_crime, container, false);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CrimeRecyclerViewAdapter(crime -> presenter.clickOnItem(crime), (crime, view) -> {
            PopupMenu popup = new PopupMenu(view.getContext(), view);
            popup.inflate(R.menu.crime_list_item_context);
            popup.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.menu_item_delete_crime:
                        presenter.clickDeleteCrime(crime);
                        return true;
                    default:
                        return false;
                }
            });
            popup.show();
        });
        recyclerView.setAdapter(adapter);
        return v;
    }


    public void updateUI() {
        presenter.init();
    }


    @Override
    public void showDetailsCrime(Crime crime) {
        mCallbacks.onCrimeSelected(crime);
    }
}
