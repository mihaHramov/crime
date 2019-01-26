package com.example.miha.criminalintent.domain.global;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import rx.Observable;
import rx.Single;

public class FirebaseFileTransfer implements IFileTransfer {
    private StorageReference mStorageRef;

    public FirebaseFileTransfer(StorageReference mStorageRef) {
        this.mStorageRef = mStorageRef;
    }

    @Override
    public Observable<String> sendFileToServer(String photo) {

        return Single.create((Single.OnSubscribe<String>) singleSubscriber -> {
            Uri file = Uri.fromFile(new File(photo));
            StorageReference riversRef = mStorageRef.child("images/rivers1.jpg");
            riversRef.putFile(file)
                    .addOnSuccessListener(taskSnapshot -> {
                                UploadTask.TaskSnapshot temp = taskSnapshot;
                                taskSnapshot.getMetadata();
                                taskSnapshot.getUploadSessionUri();

                                singleSubscriber.onSuccess(taskSnapshot.getUploadSessionUri().getEncodedPath());
                            }
                    )
                    .addOnFailureListener(e -> singleSubscriber.onError(new Throwable(e.getMessage())));
        }).toObservable();
    }
}
