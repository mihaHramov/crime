package com.example.miha.criminalintent.data.repositories.repositoryOfCrime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.miha.criminalintent.domain.model.Comment;
import com.example.miha.criminalintent.domain.model.Crime;
import com.example.miha.criminalintent.domain.model.ItemCrime;
import com.example.miha.criminalintent.domain.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class BdRepositoryOfCrime extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
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
    private static final String CRIME_DETAILS = "details";
    private static final String CRIME_PHOTO = "photo";
    private static final String CRIME_SOLVED = "solved";
    private static final String CRIME_AUTHOR = "author";
    private static final String CRIME_SUSPECT = "suspect";
    private static final String CRIME_PUBLIC = "public";

    public BdRepositoryOfCrime(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private String getColumnName(String tableName, String columnName) {
        return tableName + "_" + columnName;
    }

    private String getSelectCulumn(Map<String, List<String>> map) {
        String select = "SELECT ";
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String val : entry.getValue()) {
                select = select.concat(entry.getKey() + "." + val + " AS " + getColumnName(entry.getKey(), val) + " , ");
            }
        }
        select = select.trim();
        return select.substring(0, select.length() - 1);
    }

    public List<ItemCrime> getCrimes() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<ItemCrime> crimeList = new ArrayList<>();

        String crimeAs = "crime";
        String authorAs = "author";
        String userSuspect = "suspect";

        String authorName = getColumnName(authorAs, USER_NAME);
        String authorid = getColumnName(authorAs, USER_ID);
        String authorPhoto = getColumnName(authorAs, USER_PHOTO);
        String authorUrlId = getColumnName(authorAs, USER_URL_ID);

        String suspectName = getColumnName(userSuspect, USER_NAME);
        String suspectid = getColumnName(userSuspect, USER_ID);
        String suspectPhoto = getColumnName(userSuspect, USER_PHOTO);
        String suspectUrlId = getColumnName(userSuspect, USER_URL_ID);

        String crimeName = getColumnName(crimeAs, CRIME_TITLE);
        String crimeDate = getColumnName(crimeAs, CRIME_DATE);
        String crimePhoto = getColumnName(crimeAs, CRIME_PHOTO);
        String crimeSolved = getColumnName(crimeAs, CRIME_SOLVED);
        String crimeId = getColumnName(crimeAs, CRIME_ID);
        String crimeDetails = getColumnName(crimeAs, CRIME_DETAILS);
        String crimePublic = getColumnName(crimeAs, CRIME_PUBLIC);
//        Map<String, List<String>> listSelect = new Hashtable<>();
//        listSelect.put(authorAs, Arrays.asList(USER_NAME, USER_ID, USER_PHOTO, USER_URL_ID));
//        listSelect.put(userSuspect, Arrays.asList(USER_NAME, USER_ID, USER_PHOTO, USER_URL_ID));
//        listSelect.put(crimeAs, Arrays.asList(CRIME_TITLE, CRIME_DATE, CRIME_PHOTO, CRIME_SOLVED, CRIME_ID, CRIME_DETAILS, CRIME_PUBLIC));

        String select = "SELECT " +
                authorAs + "." + USER_NAME + " AS " + authorName + " , " +
                authorAs + "." + USER_ID + " AS " + authorid + " , " +
                authorAs + "." + USER_PHOTO + " AS " + authorPhoto + " , " +
                authorAs + "." + USER_URL_ID + " AS " + authorUrlId + " , " +

                userSuspect + "." + USER_NAME + " AS " + suspectName + " , " +
                userSuspect + "." + USER_ID + " AS " + suspectid + " , " +
                userSuspect + "." + USER_PHOTO + " AS " + suspectPhoto + " , " +
                userSuspect + "." + USER_URL_ID + " AS " + suspectUrlId + " , " +

                crimeAs + "." + CRIME_TITLE + " AS " + crimeName + " , " +
                crimeAs + "." + CRIME_DATE + " AS " + crimeDate + " , " +
                crimeAs + "." + CRIME_PHOTO + " AS " + crimePhoto + " , " +
                crimeAs + "." + CRIME_SOLVED + " AS " + crimeSolved + " , " +
                crimeAs + "." + CRIME_DETAILS + " AS " + crimeDetails + " , " +
                crimeAs + "." + CRIME_PUBLIC + " AS " + crimePublic + " , " +
                crimeAs + "." + CRIME_ID + " AS " + crimeId;
        String from = " FROM " + CRIME_TABLE + " AS " + crimeAs +
                " LEFT JOIN " + USER_TABLE + " AS " + authorAs + " ON " + crimeAs + "." + CRIME_AUTHOR + "=" + authorAs + "." + USER_ID +
                " LEFT JOIN " + USER_TABLE + " AS " + userSuspect + " ON " + crimeAs + "." + CRIME_SUSPECT + "=" + userSuspect + "." + USER_ID;


        Cursor cursor = db.rawQuery(select + from, null);
        if (cursor.moveToFirst()) {
            Integer authoeNameidCol = cursor.getColumnIndex(authorName);
            Integer authorIdCol = cursor.getColumnIndex(authorid);
            Integer authorPhotoCol = cursor.getColumnIndex(authorPhoto);
            Integer authorServerIdCol = cursor.getColumnIndex(authorUrlId);

            Integer suspectNameidCol = cursor.getColumnIndex(suspectName);
            Integer suspectIdCol = cursor.getColumnIndex(suspectid);
            Integer suspectPhotoCol = cursor.getColumnIndex(suspectPhoto);
            Integer suspectServerIdCol = cursor.getColumnIndex(suspectUrlId);


            Integer titleIdCol = cursor.getColumnIndex(crimeName);
            Integer crimeDateCol = cursor.getColumnIndex(crimeDate);
            Integer crimePhotoColl = cursor.getColumnIndex(crimePhoto);
            Integer crimeisSolvedColl = cursor.getColumnIndex(crimeSolved);
            Integer crimeIdColl = cursor.getColumnIndex(crimeId);
            Integer crimeDetailsColl = cursor.getColumnIndex(crimeDetails);
            Integer crimePubliclsColl = cursor.getColumnIndex(crimePublic);
            String temp = "";
            Integer number = 0;
            do {
                User author = getUserFromCrime(cursor, authoeNameidCol, authorIdCol, authorPhotoCol, authorServerIdCol);
                User suspect = getUserFromCrime(cursor, suspectNameidCol, suspectIdCol, suspectPhotoCol, suspectServerIdCol);
                Crime crime = getCrime(cursor, titleIdCol, crimeDateCol, crimePhotoColl, crimeisSolvedColl, crimeIdColl, crimeDetailsColl, crimePubliclsColl);
                crime.setAuthor(author);
                crime.setSuspect(suspect);
                crime.setComments(new ArrayList<>());
                ItemCrime itemCrime = new ItemCrime();
                itemCrime.setPosition(number);
                itemCrime.setCrime(crime);
                crimeList.add(itemCrime);
                temp = temp.concat(crime.getId() + ",");
                number++;
            } while (cursor.moveToNext());
            temp = temp.substring(0, temp.length() - 1);


            String message = "message";
            String commentId = "comment_id";
            String commentData = "comment_data";
            String commentIdCrime = "crime_id";

            //getComments
            String selectComments = " SELECT  " +
                    COMMENT_TABLE + "." + COMMENT_MESSAGE + " AS " + message + " , " +
                    COMMENT_TABLE + "." + COMMENT_ID + " AS " + commentId + " , " +
                    COMMENT_TABLE + "." + COMMENT_DATA + " AS " + commentData + " , " +
                    COMMENT_TABLE + "." + COMMENT_CRIME + " AS " + commentIdCrime + " , " +
                    authorAs + "." + USER_NAME + " AS " + authorName + " , " +
                    authorAs + "." + USER_ID + " AS " + authorid + " , " +
                    authorAs + "." + USER_PHOTO + " AS " + authorPhoto + " , " +
                    authorAs + "." + USER_URL_ID + " AS " + authorUrlId;


            String columnComments = " FROM " + COMMENT_TABLE + " INNER JOIN " + USER_TABLE + " AS " + authorAs +
                    " ON " + COMMENT_TABLE + "." + COMMENT_USER + "=" + authorid;

            String whereComments = " WHERE " + commentIdCrime + " IN (" + temp + ")";
            String orderBy = " ORDER BY " + commentIdCrime;


            Cursor cursorComment = db.rawQuery(selectComments + columnComments + whereComments + orderBy, null);
            if (cursorComment.moveToFirst()) {
                Integer messageCol = cursorComment.getColumnIndex(COMMENT_TABLE + "." + COMMENT_MESSAGE);
                Integer dataCol = cursorComment.getColumnIndex(COMMENT_TABLE + "." + COMMENT_DATA);
                Integer crimeIdCol = cursorComment.getColumnIndex(COMMENT_TABLE + "." + COMMENT_CRIME);
                Integer idCommentCol = cursorComment.getColumnIndex(commentId);

                Integer authorCol = cursorComment.getColumnIndex(authorName);
                Integer authoridCol = cursorComment.getColumnIndex(authorid);
                Integer photoCol = cursorComment.getColumnIndex(authorPhoto);
                Integer urlId = cursorComment.getColumnIndex(authorUrlId);

                Map<Integer, List<Comment>> map = new Hashtable<>();
                do {
                    Comment comment = new Comment();
                    comment.setDate(cursorComment.getString(dataCol));
                    comment.setMessage(cursorComment.getString(messageCol));
                    comment.setId(cursorComment.getInt(idCommentCol));
                    Integer idCrime = cursorComment.getInt(crimeIdCol);
                    User user = getUserFromCrime(cursorComment, authorCol, authoridCol, photoCol, urlId);
                    comment.setAuthor(user);
                    if (!map.containsKey(idCrime)) {
                        List<Comment> comments = new ArrayList<>();
                        comments.add(comment);
                        map.put(idCrime, comments);
                    } else {
                        map.get(idCrime).add(comment);
                    }

                } while (cursorComment.moveToNext());

                for (ItemCrime cr : crimeList) {
                    if (map.containsKey(cr.getCrime().getId())) {
                        cr.getCrime().setComments(map.get(cr.getCrime().getId()));
                    }
                }
            }
            cursorComment.close();
        }
        cursor.close();
        return crimeList;
    }

    private Crime getCrime(Cursor cursor,
                           Integer titleIdCol,
                           Integer crimeDateCol,
                           Integer crimePhotoColl,
                           Integer crimeisSolvedColl,
                           Integer crimeIdColl,
                           Integer crimeDetailsColl,
                           Integer crimePubliclsColl) {
        Crime crime = new Crime();
        crime.setTitle(cursor.getString(titleIdCol));
        crime.setDate(cursor.getString(crimeDateCol));
        crime.setPhoto(cursor.getString(crimePhotoColl));
        crime.setSolved(cursor.getString(crimeisSolvedColl).equals("1"));
        String str = cursor.getString(crimePubliclsColl);
        crime.setPublick(str.equals("1"));
        crime.setId(cursor.getInt(crimeIdColl));
        crime.setDetails(cursor.getString(crimeDetailsColl));
        return crime;
    }

    private User getUserFromCrime(Cursor cursor, Integer authoeNameidCol, Integer authorIdCol, Integer authorPhotoCol, Integer authorServerIdCol) {
        User author = new User();
        author.setName(getSting(authoeNameidCol, cursor));
        author.setId(cursor.getInt(authorIdCol));
        author.setPhoto(getSting(authorPhotoCol, cursor));
        author.setServerId(getSting(authorServerIdCol, cursor));
        return author;
    }

    private String getSting(Integer id, Cursor cursor) {
        return cursor.getString(id);
    }

    public Boolean update(Crime crime) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CRIME_PHOTO, crime.getPhoto());
        cv.put(CRIME_DATE, crime.getDate());
        cv.put(CRIME_SOLVED, crime.getSolved());
        cv.put(CRIME_TITLE, crime.getTitle());
        cv.put(CRIME_DETAILS, crime.getDetails());
        if (crime.getAuthor() != null) {
            cv.put(CRIME_AUTHOR, crime.getAuthor().getId());
        }
        if (crime.getSuspect() != null) {
            cv.put(CRIME_SUSPECT, crime.getSuspect().getId());
        }
        int updCount = db.update(CRIME_TABLE, cv, CRIME_ID + " = ?",
                new String[]{crime.getId().toString()});
        db.close();
        return updCount > 0;
    }

    public Boolean delete(Crime crime) {
        return null;
    }

    public Crime create() {
        SQLiteDatabase db = getWritableDatabase();
        Crime crime = new Crime();
        crime.setTitle(CRIME_TITLE);
        crime.setPhoto("");
        Date date = new Date();
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String datetime = format.format(date);
        crime.setDate(datetime);
        crime.setSolved(false);
        crime.setPublick(false);
        crime.setDetails(CRIME_DETAILS);
        crime.setComments(new ArrayList<>());
        ContentValues cv = new ContentValues();

        cv.put(CRIME_TITLE, crime.getTitle());
        cv.put(CRIME_SOLVED, crime.getSolved());
        cv.put(CRIME_DATE, crime.getDate());
        cv.put(CRIME_PHOTO, crime.getPhoto());
        cv.put(CRIME_AUTHOR, 0);
        cv.put(CRIME_PUBLIC, crime.getPublick());
        cv.put(CRIME_DETAILS, crime.getDetails());
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
                + CRIME_PUBLIC + " BOOLEAN,"
                + CRIME_DATE + " TEXT,"
                + CRIME_DETAILS + " TEXT,"
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

    public User create(User user) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_URL_ID + " = '" + user.getServerId() + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            Integer id = cursor.getColumnIndex(USER_ID);
            Integer idUser = cursor.getInt(id);
            user.setId(idUser);
        } else {
            ContentValues cv = new ContentValues();
            cv.put(USER_NAME, user.getName());
            cv.put(USER_PHOTO, user.getPhoto());
            cv.put(USER_URL_ID, user.getServerId());
            user.setId((int) db.insert(USER_TABLE, null, cv));

        }
        db.close();
        return user;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectComments = " SELECT  * FROM " + USER_TABLE;
        Cursor cursor = db.rawQuery(selectComments, null);
        if (cursor.moveToFirst()) {
            Integer nameCol = cursor.getColumnIndex(USER_NAME);
            Integer photoColl = cursor.getColumnIndex(USER_PHOTO);
            Integer urlColl = cursor.getColumnIndex(USER_URL_ID);
            Integer idColl = cursor.getColumnIndex(USER_ID);
            do {
                User user = new User();
                user.setName(cursor.getString(nameCol));
                user.setPhoto(cursor.getString(photoColl));
                user.setServerId(cursor.getString(urlColl));
                user.setId(cursor.getInt(idColl));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }
}
