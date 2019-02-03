package com.example.miha.criminalintent.presentation.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.example.miha.criminalintent.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListFragment extends MvpAppCompatFragment {
    @BindView(R.id.user_list)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

}
