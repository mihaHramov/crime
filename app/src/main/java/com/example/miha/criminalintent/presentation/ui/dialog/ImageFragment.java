package com.example.miha.criminalintent.presentation.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatDialogFragment;
import com.example.miha.criminalintent.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageFragment extends MvpAppCompatDialogFragment {
    private static final String EXTRA_IMAGE_PATH = ImageFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_image, container, false);
        if (getArguments() != null) {
            PhotoView mImageView = v.findViewById(R.id.image);
            String path = (String) getArguments().getSerializable(EXTRA_IMAGE_PATH);
            Picasso.get()
                    .load(path)
                    .resizeDimen(R.dimen.image_size,R.dimen.image_size)
                    .into(mImageView);
        }
        return v;
    }


    public static ImageFragment newInstance(String imagePath) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_IMAGE_PATH, imagePath);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
