package com.mlab.kabelo.studentreport;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    SQLiteDatabase db;

    private static final String TABLE_CREATE ="create table contacts(id integer primary key not null ," +
            " name text not null, email text not null, username text not null, password text not null );";




    //TODO auto-generated constructor stub
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;


    }
    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //counting the contacts
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , c.getName());
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_USERNAME , c.getUsername());
        values.put(COLUMN_PASSWORD , c.getPassword());


        db.insert(TABLE_NAME, null , values);
        db.close();
    }

    public String searchPass(String username){

        db = this.getReadableDatabase();
        String query = "select username,password from contacts";
        Cursor cursor = db.rawQuery(query, null);


        String a,b;
        b = "not found";
        if (cursor.moveToFirst()){
            do {
                a = cursor.getString(0);


                if (a.equals(username)){
                    b= cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ildVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME ;
        db.execSQL(query);
        this.onCreate(db);

    }



}

