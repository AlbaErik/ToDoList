package com.nahuelmaikafanessi.notes_app.DB_Managment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Table Name
    public static final String TABLE_NAME = "COUNTRIES";
    //Table Columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";

    //Database Information
    static final String DN_NAME = "NOTES_APP.DB";

    //Database Version (Cuando haces un update de la tabla, es necesario cambiar el nro de version)
    static final int DB_VERSION = 1;


    //Creating Table Query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SUBJECT + " TEXT NOT NULL, " +
            DESC + " TEXT);";

    //Constructor
    public DatabaseHelper(Context context) {
        super(context,DN_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Executing the Query
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
