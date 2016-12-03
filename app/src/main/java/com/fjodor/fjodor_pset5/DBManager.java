package com.fjodor.fjodor_pset5;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.MENU_NAME, name);
        database.insert(DatabaseHelper.TABLE_MENU, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.MENU_NAME };
        Cursor cursor = database.query(DatabaseHelper.TABLE_MENU, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MENU_NAME, name);
        int i = database.update(DatabaseHelper.TABLE_MENU, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_MENU, DatabaseHelper._ID + "=" + _id, null);
    }

}