package com.julian.service.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julian on 2019/5/8.
 * Description:
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "book_list.db";

    public static final String TABLE_NAME = "Book";


    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " name TEXT)";

    public DbHelper(Context context) {
        this(context, DB_NAME, null, 1);
    }

    private DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
