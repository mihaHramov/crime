package com.example.miha.criminalintent.presentation.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.miha.criminalintent.R;
import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.presentation.mvp.authUserFragment.AuthUserFragmentPresenter;
import com.example.miha.criminalintent.presentation.mvp.authUserFragment.IAuthUserFragmentView;
import com.example.miha.criminalintent.presentation.ui.ApplicationCrime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthFragmentDialog extends MvpAppCompatDialogFragment implements IAuthUserFragmentView {
    @InjectPresenter
    AuthUserFragmentPresenter presenter;

    @ProvidePresenter
    AuthUserFragmentPresenter provide() {
        return ApplicationCrime.getAuthComponent().getPresenter();
    }

    @BindView(R.id.progress)
    ProgressBar mProgress;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.email)
    EditText mEmail;

    @BindView(R.id.new_user)
    CheckBox isNewUser;

    @OnClick(R.id.auth)
    public void authUser() {
        presenter.auth(getString(mEmail), getString(mPassword), isNewUser.isChecked());
    }

    @BindView(R.id.auth)
    Button mAuth;

    @Override
    public void showUser(User user) {
        Toast.makeText(getActivity(), "Здравствуйте " + user.getName(), Toast.LENGTH_LONG).show();
        dismiss();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auth_fragment_dialog, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    private String getString(EditText editText) {
        return editText.getText().toString();
    }

    @Override
    public void disableView(Boolean b) {
        isNewUser.setEnabled(b);
        mPassword.setEnabled(b);
        mEmail.setEnabled(b);
        mAuth.setEnabled(b);
        mProgress.setVisibility(b?View.GONE:View.VISIBLE);
    }
}
