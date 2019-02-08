package com.example.miha.criminalintent.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsListActivity.CommentListActivityPresenter;
import com.example.miha.criminalintent.presentation.mvp.commentsListActivity.CommentsListActivityView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.example.miha.criminalintent.presentation.ui.fragment.CommentListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentActivity extends MvpAppCompatActivity implements CommentsListActivityView {
    private static final String KEY = CommentActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @InjectPresenter
    CommentListActivityPresenter presenter;

    @ProvidePresenter
    CommentListActivityPresenter provideCommentListActivityPresenter() {
        Crime crime = (Crime) getIntent().getSerializableExtra(KEY);
        presenter = ApplicationCrime.getCommentsListActivityComponent(crime).getPresenter();
        presenter.init();
        return presenter;
    }

    @OnClick(R.id.fab)
    void clickOnAddComments(View view) {
        presenter.createComments();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void showComments(Crime crime) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, CommentListFragment.newInstance(crime))
                .commit();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFormComments() {

    }

    public static void startActivity(Crime crime, Context context) {
        Intent i = new Intent(context, CommentActivity.class);
        i.putExtra(KEY, crime);
        context.startActivity(i);
    }
}
