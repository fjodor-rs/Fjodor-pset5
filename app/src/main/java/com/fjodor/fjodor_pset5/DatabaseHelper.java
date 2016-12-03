package com.fjodor.fjodor_pset5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_TODO = "TODO_LIST";
    public static final String TABLE_MENU = "TABLE_MENU";

    public static final String CREATE_TIME = "created_on";
    public static final String _ID = "_id";
    public static final String TODO = "todo";
    public static final String MENU_NAME = "menu_name";
    public static final String TODO_ID = "todo_id";
    public static final String MENU_ID = "menu_id";

    static final String DB_NAME = "TODO_DATABASE.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_MENU = "create table "
            + TABLE_MENU + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MENU_NAME + " TEXT, "
            + CREATE_TIME + " DATETIME " + "};";

    private static final String CREATE_TABLE_TODO = "create table " +
            TABLE_TODO + "("
            + TODO_ID + " INTEGER, "
            + MENU_ID + " INTEGER, "
            + TODO + " TEXT, "
            + CREATE_TIME + " DATETIME "
            + " FOREIGN KEY ("+MENU_ID+") REFERENCES "+CREATE_TABLE_MENU+"("+_ID+"));";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_MENU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);

        onCreate(db);
    }
}