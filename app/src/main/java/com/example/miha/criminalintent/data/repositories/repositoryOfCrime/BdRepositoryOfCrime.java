package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.miha.criminalintent.domain.model.Crime;

import java.util.ArrayList;
import java.util.List;


public class BdRepositoryOfCrime extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrimeDb";

    private static final String USER_TABLE = "users";
    private static final String USER_NAME = "name";
    private static final String USER_PHOTO = "proto";
    private static final String USER_URL_ID = "userId";
    private static final String USER_ID = "id";


    private static final String COMMENT_TABLE = "comments";
    private static final String COMMENT_USER = "user";
    private static final String COMMENT_ID = "id";
    private static final String COMMENT_DATA = "data";
    private static final String COMMENT_MESSAGE = "message";
    private static final String COMMENT_CRIME = "crime";


    private static final String CRIME_TABLE = "crime";
    private static final String CRIME_ID = "id";
    private static final String CRIME_DATE = "date";
    private static final String CRIME_TITLE = "title";
    private static final String CRIME_PHOTO = "photo";
    private static final String CRIME_SOLVED = "solved";
    private static final String CRIME_AUTHOR = "author";
    private static final String CRIME_SUSPECT = "suspect";

    public BdRepositoryOfCrime(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Crime> getCrimes() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Crime> crimeList = new ArrayList<>();
        String authorName = "author_name";
        String crimeTitle = "crime_name";
        String suspectName = "suspect_name";


        String crimeAs = "crimeAs";
        String userAs = "userAs";
        String userSuspect = "suspectAs";

        String select = "SELECT " +
                userAs + "." + USER_NAME + " AS " + authorName + " , " +
                crimeAs + "." + CRIME_TITLE + " AS " + crimeTitle + " , " +
                userSuspect + "." + USER_NAME + " AS " + suspectName;
        String from = " FROM " + CRIME_TABLE + " AS " + crimeAs +
                " INNER JOIN " + USER_TABLE + " AS " + userAs + " ON " + crimeAs + "." + CRIME_AUTHOR + "=" + userAs + "." + USER_ID +
                " LEFT JOIN " + USER_TABLE + " AS " + userSuspect + " ON " + crimeAs + "." + CRIME_SUSPECT + "=" + userSuspect + "." + USER_ID;


        Cursor cursor = db.rawQuery(select + from, null);
        if (cursor.moveToFirst()) {

        }
        cursor.close();


        for (int i = 0; i < 30; i++) {
            Crime crime = new Crime();
            crime.setTitle("temp" + i);
            crime.setId(i);
            crime.setSolved(false);
            crime.setPhoto("photo");
            crimeList.add(crime);
        }
        return crimeList;
    }


    public Boolean update(Crime crime) {
        return null;
    }

    public Boolean delete(Crime crime) {
        return null;
    }

    public Crime create() {
        SQLiteDatabase db = getWritableDatabase();
        Crime crime = new Crime();
        crime.setTitle(CRIME_TITLE);
        ContentValues cv = new ContentValues();
        cv.put(CRIME_TITLE, crime.getTitle());
        crime.setId((int) db.insert(CRIME_TABLE, null, cv));
        db.close();
        return crime;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "("
                + USER_ID + " INTEGER PRIMARY KEY,"
                + USER_NAME + " TEXT,"
                + USER_PHOTO + " TEXT,"
                + USER_URL_ID + " TEXT)";


        String CREATE_COMMENT_TABLE = "CREATE TABLE " + COMMENT_TABLE + "("
                + COMMENT_ID + " INTEGER PRIMARY KEY,"
                + COMMENT_USER + " INTEGER,"
                + COMMENT_CRIME + " INTEGER,"
                + COMMENT_DATA + " TEXT,"
                + COMMENT_MESSAGE + " TEXT)";


        String CREATE_CRIME_TABLE = "CREATE TABLE " + CRIME_TABLE + "("
                + CRIME_ID + " INTEGER PRIMARY KEY,"
                + CRIME_SUSPECT + " INTEGER,"
                + CRIME_AUTHOR + " INTEGER,"
                + CRIME_SOLVED + " BOOLEAN,"
                + CRIME_DATE + " TEXT,"
                + CRIME_PHOTO + " TEXT,"
                + CRIME_TITLE + " TEXT)";

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_COMMENT_TABLE);
        db.execSQL(CREATE_CRIME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + COMMENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CRIME_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }
}
