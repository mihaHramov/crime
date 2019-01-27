package com.example.miha.criminalintent.data.network.user;

import com.example.miha.criminalintent.domain.model.User;
import com.example.miha.criminalintent.domain.model.UserOnServer;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import rx.Observable;
import rx.Single;


public class UserStorage implements UserStorageApi {
    private static final String TABLE = "users";
    private DatabaseReference databaseReference;

    public UserStorage(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable<User> createUser(User user) {
        return Single.create((Single.OnSubscribe<User>) singleSubscriber -> {
            UserOnServer userOnServer = new UserOnServer(user.getName(), user.getPhoto());
            databaseReference.child(TABLE).child(user.getServerId()).setValue(userOnServer)
                    .addOnSuccessListener(aVoid -> singleSubscriber.onSuccess(user))
                    .addOnFailureListener(e -> singleSubscriber.onError(new Throwable(e.getMessage())));
            //String st = databaseReference.child(TABLE).push().getKey();
        }).toObservable();
    }

    @Override
    public Observable<List<User>> getAllUser() {
        return null;
    }
}
