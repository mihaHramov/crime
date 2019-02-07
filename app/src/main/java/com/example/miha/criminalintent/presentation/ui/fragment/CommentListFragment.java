package com.example.miha.criminalintent.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsListFragment.CommentsListFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.commentsListFragment.CommentsListFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.adapter.CommentsRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentListFragment extends MvpAppCompatFragment implements CommentsListFragmentView {
    private static final String KEY = CommentListFragment.class.getName();
    @BindView(R.id.list)
    RecyclerView recyclerView;
    @BindView(R.id.loading)
    ProgressBar progress;

    CommentsRecyclerViewAdapter adapter;

    @InjectPresenter
    CommentsListFragmentPresenter presenter;

    @ProvidePresenter
    CommentsListFragmentPresenter provideCommentsListFragmentPresenter() {
        if (getArguments() != null) {
            Crime crime = (Crime) getArguments().getSerializable(KEY);
            presenter = ApplicationCrime.getCommentsComponent(crime).getPresenter();
        }
        presenter.init();
        return presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CommentsRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        return v;
    }

    public static CommentListFragment newInstance(Crime crime) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, crime);
        CommentListFragment fragment = new CommentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void showComments(List<Comment> comments) {
        adapter.setComments(comments);
    }

    @Override
    public void showLoading(Boolean flag) {
        progress.setVisibility(flag ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
