package com.example.miha.criminalintent.presentation.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.mvp.userListActivity.UserListActivityPresenter;
import com.example.miha.criminalintent.presentation.mvp.userListActivity.UserListActivityView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.adapter.UserRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListFragment extends MvpAppCompatFragment implements UserListActivityView {
    @BindView(R.id.user_list)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @Inject
    UserRecyclerViewAdapter adapter;

    @InjectPresenter
    UserListActivityPresenter presenter;

    @ProvidePresenter
    UserListActivityPresenter provideUserListActivityPresenter() {
        presenter = ApplicationCrime.getUserListActivityComponent().provideUserListActivityPresenter();
        presenter.init();
        return presenter;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, v);
        ApplicationCrime.getUserListActivityComponent().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void showUsers(List<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoading(Boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }
}
