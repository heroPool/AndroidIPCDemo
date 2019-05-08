package com.julian.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.julian.service.db.DbHelper;

public class BookContentProvider extends ContentProvider {
    private static final String TAG = BookContentProvider.class.getSimpleName();
    private DbHelper dbHelper;

    public BookContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return -1;
    }

    @Override
    public String getType(Uri uri) {
        // 多媒体类型
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        dbHelper.getWritableDatabase().insert("Book", null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate() called");
        dbHelper = new DbHelper(getContext());
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        writableDatabase.execSQL("delete from Book");
        writableDatabase.execSQL("insert into Book values(1,'Android全埋点进阶')");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return dbHelper.getWritableDatabase().query("Book", projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return -1;
    }
}
