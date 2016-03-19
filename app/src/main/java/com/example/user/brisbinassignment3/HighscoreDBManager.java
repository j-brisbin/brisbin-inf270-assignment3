package com.example.user.brisbinassignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haungsn on 10/26/2015.
 */
public class HighscoreDBManager extends SQLiteOpenHelper {

    public static final String HS_TABLE_NAME = "highscore";
    public static final String HS_ID = "_id";
    public static final String HS_USERNAME_ID = "username_id";
    public static final String HS_SCORE = "score";
    public static final String HS_TIME_STAMP = "time_stamp";
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "username";

    public HighscoreDBManager(Context context){
        super(context,
                /*db name=*/ "highscoresdb",
                /*cursorFactory=*/ null,
                /*db version=*/1);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("db", "onCreate");
        String sql = "CREATE TABLE " + USER_TABLE_NAME
                + " (" + USER_ID + " INTEGER, "
                + USER_NAME + " TEXT,"
                + " PRIMARY KEY (" + USER_ID + "));";
        db.execSQL(sql);
        sql = "CREATE TABLE " + HS_TABLE_NAME
                + " (" + HS_ID + " INTEGER, "
                + HS_USERNAME_ID + " INTEGER,"
                + HS_SCORE + " INTEGER,"
                + HS_TIME_STAMP + " INTEGER,"
                + " PRIMARY KEY (" + HS_ID + ")"
                + "FOREIGN KEY(" + HS_USERNAME_ID + ") REFERENCES " +
                USER_TABLE_NAME + "(" + USER_ID + "));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + " AND " + HS_TABLE_NAME);
        onCreate(db);
    }

    public User addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id = checkUserID(user.getUsername());
        if(id == -1){
            values.put(USER_NAME, user.getUsername());
            id = db.insert(USER_TABLE_NAME, null, values);
            user.setId(id);
            db.close();
            return user;
        }
        else{
            user.setId(id);
            db.close();
            return user;
        }
    }

    public HighScore addHighScore(HighScore highScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HS_USERNAME_ID,highScore.getUserId());
        values.put(HS_SCORE,highScore.getHighScore());
        values.put(HS_TIME_STAMP,highScore.getTimeStamp());
        long id = db.insert(HS_TABLE_NAME, null, values);
        highScore.setId(id);
        db.close();
        return highScore;
    }

    public long checkUserID(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE " + USER_NAME + "='"+username+"'";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return cursor.getLong(0);
        }
        return -1;
    }

    public User getUser(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE_NAME, new String[]{USER_ID, USER_NAME}, USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            User user = new User(
                    cursor.getString(1)
            );
            user.setId(cursor.getLong(0));
            return user;
        }
        return null;
    }

    public Cursor getAllHighScoresByUser(long userId){
        SQLiteDatabase db = this.getWritableDatabase();
        List<HighScore> highScores = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + HS_TABLE_NAME + " WHERE " + HS_USERNAME_ID + "='" + userId+"' " +
                "ORDER BY " + HS_SCORE + " DESC;";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getAllHighScoresByUser(long userId,int limit){
        SQLiteDatabase db = this.getWritableDatabase();
        List<HighScore> highScores = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + HS_TABLE_NAME + " WHERE " + HS_USERNAME_ID + "='" + userId+"' " +
                "ORDER BY " + HS_SCORE + " DESC LIMIT " + limit + ";";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getJoinCursor(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + USER_TABLE_NAME + "." + USER_ID + ", " + USER_TABLE_NAME + "." + USER_NAME + ", " + HS_TABLE_NAME + "." + HS_SCORE + " FROM " +
                USER_TABLE_NAME + " INNER JOIN " + HS_TABLE_NAME + " ON " + USER_TABLE_NAME + "." + USER_ID + "=" +
                HS_TABLE_NAME + "." + HS_USERNAME_ID + " ORDER BY " + HS_TABLE_NAME + "." + HS_SCORE + " DESC LIMIT 5";
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

}
