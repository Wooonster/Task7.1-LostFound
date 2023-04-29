package com.example.lostfound.dbSettings;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.lostfound.entities.PostItem;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LostFound.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_SQL =
                "CREATE TABLE " + LostFoundContract.PostEntry.TABLE_NAME + " (" +
                LostFoundContract.PostEntry.POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LostFoundContract.PostEntry.POST_TYPE + "TEXT, " +
                LostFoundContract.PostEntry.NAME + "TEXT, " +
                LostFoundContract.PostEntry.PHONE + "INTEGER, " +
                LostFoundContract.PostEntry.DESCRIPTION + "TEXT, " +
                LostFoundContract.PostEntry.DATE + "TEXT, " +
                LostFoundContract.PostEntry.LOCATION + "TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String DELETE_TABLE_SQL = "DROP TABLE IF EXISTS " + LostFoundContract.PostEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(DELETE_TABLE_SQL);
        onCreate(sqLiteDatabase);
    }

    public long insertPost(PostItem postItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // insert
        values.put(LostFoundContract.PostEntry.POST_TYPE, postItem.isPostType());
        values.put(LostFoundContract.PostEntry.NAME, postItem.getName());
        values.put(LostFoundContract.PostEntry.PHONE, postItem.getPhone());
        values.put(LostFoundContract.PostEntry.DESCRIPTION, postItem.getDescription());
        values.put(LostFoundContract.PostEntry.DATE, postItem.getDate());
        values.put(LostFoundContract.PostEntry.LOCATION, postItem.getLocation());

        long rowId = db.insert(LostFoundContract.PostEntry.TABLE_NAME, null, values);

        return rowId;
    }

}
