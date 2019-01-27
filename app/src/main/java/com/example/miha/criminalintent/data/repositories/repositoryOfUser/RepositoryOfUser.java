package com.example.miha.criminalintent.data.repositories.repositoryOfUser;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.miha.criminalintent.data.repositories.repositoryOfCrime.BdRepositoryOfCrime;
import com.example.miha.criminalintent.domain.model.User;

import rx.Observable;

public class RepositoryOfUser implements IRepositoryOfUser {
    private static final String MY_SETTINGS = "my_settings";
    private static final String USER_NAME = "user_name";
    private static final String USER_PHOTO = "user_photo";
    private static final String USER_ID = "id";
    private static final String USER_SERVER_ID = "id_server";
    private SharedPreferences sp;
    private BdRepositoryOfCrime localDb;

    public RepositoryOfUser(Context context,BdRepositoryOfCrime localDb) {
        sp = context.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
        this.localDb = localDb;
    }

    @Override
    public Observable<User> getCurrentUser() {
        return Observable.fromCallable(() -> {
            User user = new User();
            user.setName(sp.getString(USER_NAME, ""));
            user.setId(sp.getInt(USER_ID, 0));
            user.setServerId(sp.getString(USER_SERVER_ID, ""));
            user.setPhoto(sp.getString(USER_PHOTO, ""));
            return user;
        });
    }

    @Override
    public void setCurrentUser(User user) {
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(USER_NAME, user.getName());
        ed.putInt(USER_ID, user.getId());
        ed.putString(USER_SERVER_ID, user.getServerId());
        ed.putString(USER_PHOTO, user.getPhoto());
        ed.apply();
    }

    @Override
    public Observable<User> addUserIfNotExist(User user) {
       return Observable.fromCallable(() -> localDb.create(user));
    }
}
