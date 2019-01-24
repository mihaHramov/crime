package com.example.miha.criminalintent.presentation.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.example.miha.criminalintent.R;
import com.squareup.picasso.Picasso;

public class ImageFragment extends MvpAppCompatDialogFragment {
    public static final String EXTRA_IMAGE_PATH = "com.bignerdranch.android.criminalintent.image_path";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ImageView mImageView = (ImageView) inflater.inflate(R.layout.dialog_image, container, false);
        if (getArguments() != null) {
            String path = (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
            Picasso.get()
                    .load(path)
                    .error(R.drawable.ic_action_account_circle)
                    .into(mImageView);
        }
        return mImageView;
    }


    public static ImageFragment newInstance(String imagePath) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH, imagePath);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
