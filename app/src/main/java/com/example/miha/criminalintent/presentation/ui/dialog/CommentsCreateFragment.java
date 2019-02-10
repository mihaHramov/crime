package com.example.miha.criminalintent.presentation.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment.CommentsCreateFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.commentsCreateFragment.CommentsCreateFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsCreateFragment extends MvpAppCompatDialogFragment implements CommentsCreateFragmentView {
    private static final String KEY = CommentsCreateFragment.class.getName();
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.message)
    EditText message;

    @BindView(R.id.send_message)
    Button btn;


    @InjectPresenter
    CommentsCreateFragmentPresenter presenter;

    @ProvidePresenter
    CommentsCreateFragmentPresenter provideCommentsCreateFragmentPresenter() {
        presenter = ApplicationCrime.getCommentsCreateFragmentComponent((Crime) getArguments().getSerializable(KEY)).getPresenter();
        presenter.init();
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.create_comments, container, false);
        ButterKnife.bind(this, v);
        btn.setOnClickListener(view -> {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            presenter.sendComment(message.getText().toString(), timeStamp);
        });
        return v;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getActivity(), "show message success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setEnabled(Boolean flag) {
        message.setEnabled(flag);

    }

    @Override
    public void showUserPhoto(String photo) {
        Picasso.get().load(photo).into(image);
    }

    @Override
    public void showUserName(String nameOfUser) {
        name.setText(nameOfUser);
    }

    public static CommentsCreateFragment newInstance(Crime crime) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, crime);
        CommentsCreateFragment fragment = new CommentsCreateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}
