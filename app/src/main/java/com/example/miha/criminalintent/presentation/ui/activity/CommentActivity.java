package com.example.miha.criminalintent.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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

    public static void startActivity(Crime crime, Context context) {
        Intent i = new Intent(context, CommentActivity.class);
        i.putExtra(KEY, crime);
        context.startActivity(i);
    }
}
