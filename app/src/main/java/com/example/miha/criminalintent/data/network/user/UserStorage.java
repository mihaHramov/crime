package com.example.miha.criminalintent.data.network.user;

import android.support.annotation.NonNull;

import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.domain.model.UserOnServer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;


public class UserStorage implements UserStorageApi {
    private static final String TABLE = "users";
    private DatabaseReference databaseReference;

    public UserStorage(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable<User> createUser(User user) {
        return Single.create((Single.OnSubscribe<User>) singleSubscriber -> putUser(singleSubscriber, user)).toObservable();
    }

    private void putUser(SingleSubscriber<? super User> singleSubscriber, User user) {
        UserOnServer userOnServer = new UserOnServer(user.getName(), user.getPhoto());
        databaseReference.child(TABLE).child(user.getServerId()).setValue(userOnServer)
                .addOnSuccessListener(aVoid -> singleSubscriber.onSuccess(user))
                .addOnFailureListener(e -> singleSubscriber.onError(getError(e.getMessage())));
    }

    private Throwable getError(String ex) {
        return new Throwable(ex);
    }

    @Override
    public Observable<List<User>> getAllUser() {
        return Single.create((Single.OnSubscribe<List<User>>) this::getUsersList).toObservable();
    }

    private void getUsersList(SingleSubscriber<? super List<User>> singleSubscriber) {
        databaseReference.child(TABLE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    UserOnServer userOnServer = data.getValue(UserOnServer.class);
                    if (userOnServer != null) {
                        User user = new User();
                        user.setPhoto(userOnServer.photo);
                        user.setServerId(data.getKey());
                        user.setName(userOnServer.name);
                        user.setId(0);
                        userList.add(user);
                    }
                }
                singleSubscriber.onSuccess(userList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                singleSubscriber.onError(getError(databaseError.getMessage()));
            }
        });
    }
}
