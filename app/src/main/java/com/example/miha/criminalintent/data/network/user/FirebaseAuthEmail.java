package com.example.miha.criminalintent.data.network.user;

import com.example.miha.criminalintent.domain.mapper.FirebaseUserMaper;
import com.example.miha.criminalintent.domain.model.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import rx.Observable;
import rx.Single;

public class FirebaseAuthEmail implements UserApi {
    private FirebaseAuth mAuth;

    public FirebaseAuthEmail(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    private Observable<User> getListener(Task<AuthResult> task) {
        return Single.create((Single.OnSubscribe<FirebaseUser>) singleSubscriber ->
               task.addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                singleSubscriber.onSuccess(mAuth.getCurrentUser());
                            } else {
                                singleSubscriber.onError(new Throwable(task.getException().getMessage()));
                            }
                        }))
                .map(new FirebaseUserMaper())
                .toObservable();
    }

    @Override
    public Observable<User> login(String login, String password) {
        return getListener(mAuth.signInWithEmailAndPassword(login,password));
    }

    @Override
    public Observable<User> registration(String login, String password) {
            return getListener(mAuth.createUserWithEmailAndPassword(login,password));
        //        return Single.create((Single.OnSubscribe<FirebaseUser>) singleSubscriber ->
//                mAuth.createUserWithEmailAndPassword(login, password)
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                singleSubscriber.onSuccess(mAuth.getCurrentUser());
//                            } else {
//                                singleSubscriber.onError(new Throwable(task.getException().getMessage()));
//                            }
//                        }))
//                .map(new FirebaseUserMaper())
//                .toObservable();
    }
}
