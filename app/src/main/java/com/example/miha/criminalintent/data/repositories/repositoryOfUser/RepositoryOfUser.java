package com.example.miha.criminalintent.data.repositories.repositoryOfUser;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.miha.criminalintent.domain.model.User;

import rx.Observable;

public class RepositoryOfUser implements IRepositoryOfUser {
    private static final String MY_SETTINGS = "my_settings";
    private static final String USER_NAME = "userName";
    private static final String USER_ID = "id";
    private SharedPreferences sp;

    public RepositoryOfUser(Context context) {
        sp = context.getSharedPreferences(MY_SETTINGS, Context.MODE_PRIVATE);
    }

    @Override
    public Observable<User> getCurrentUser() {
        User user = new User();
        user.setName(sp.getString(USER_NAME,""));
        user.setId(sp.getInt(USER_ID,0));
        return Observable.just(user);
    }

    @Override
    public void setCurrentUser(User user) {
        SharedPreferences.Editor ed =   sp.edit();
        ed.putString(USER_NAME,user.getName());
        ed.putInt(USER_ID,user.getId());
        ed.apply();
    }
}
