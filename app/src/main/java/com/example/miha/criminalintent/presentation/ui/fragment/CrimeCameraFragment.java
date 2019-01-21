package com.example.miha.criminalintent.presentation.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miha.criminalintent.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by miha on 07.12.2016.
 */
public class CrimeCameraFragment extends Fragment {
    private static final String TAG = "CrimeCameraFragment";
    public static final String EXTRA_PHOTO_FILENAME = "CrimeCameraFragment";
    private View mProgressContainer;
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            mProgressContainer.setVisibility(View.VISIBLE);
        }
    };
    private Camera.PictureCallback mJpegCallback = (data, camera) -> {
        // Создание имени файла
        String filename = UUID.randomUUID().toString() + ".jpg";
        // Сохранение данных jpeg на диске
        FileOutputStream os = null;
        boolean success = true;
        try {
            os = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
/*
            Bitmap p = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap.createBitmap(p, 0, 0, p.getWidth(), p.getHeight(), matrix, true);
*/


            os.write(data);
        } catch (Exception e) {
            Log.e(TAG, "Error writing to file " + filename, e);
            success = false;
        } finally {
            try {
                if(os!=null){
                    os.close();
                }
            }
            catch (Exception e) {
                Log.e(TAG, "Error closing file " + filename, e);
                success = false;
            }
        }
        if (success) {

            Intent i = new Intent();
            i.putExtra(EXTRA_PHOTO_FILENAME, filename);
            getActivity().setResult(Activity.RESULT_OK, i);
            Log.i(TAG, "JPEG saved at " + filename);
        }else{
            getActivity().setResult(Activity.RESULT_CANCELED);
        }
        getActivity().finish();
    };


    @Override
    public void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }


    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes, int width, int height) {
        Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for (Camera.Size s : sizes) {
            int area = s.width * s.height;
            if (area > largestArea) {
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCamera = Camera.open(0);
    }

    @Nullable
    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_camera, container, false);
        Button takePictureButton =  v.findViewById(R.id.crime_camera_takePictureButton);
        takePictureButton.setOnClickListener(v1 -> {
            if(mCamera!=null){
                mCamera.takePicture(mShutterCallback,null,mJpegCallback);
            }
        });
        mSurfaceView =  v.findViewById(R.id.crime_camera_surfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (mCamera != null) {
                        mCamera.setPreviewDisplay(holder);
                    }
                } catch (IOException exception) {
                    Log.e(TAG, "Error setting up preview display", exception);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (mCamera == null) return;
                Camera.Parameters parameters = mCamera.getParameters();
                Camera.Size s = getBestSupportedSize(parameters.getSupportedPreviewSizes(), width, height);
                parameters.setPreviewSize(s.width, s.height);
                mCamera.setParameters(parameters);
                s = getBestSupportedSize(parameters.getSupportedPictureSizes(), width, height);
                parameters.setPictureSize(s.width, s.height);

                try {
                    mCamera.startPreview();
                } catch (Exception exception) {
                    Log.e(TAG, "Could not start preview", exception);
                    mCamera.release();
                    mCamera = null;
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mCamera != null) {
                    mCamera.stopPreview();
                }
            }
        });

        mProgressContainer = v.findViewById(R.id.crime_camera_progressContainer);
        mProgressContainer.setVisibility(View.INVISIBLE);
        return v;
    }
}
