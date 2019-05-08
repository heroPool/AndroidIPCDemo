package com.julian.client;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.julian.service.Book;
import com.julian.service.BookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    BookManager bookManager;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookManager = BookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    int count = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.tv);
        findViewById(R.id.btn_addbook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                try {
                    bookManager.addBook(new Book(count + ""));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_getalllist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    List<Book> bookList = bookManager.getBookList();
                    StringBuilder sb = new StringBuilder();
                    for (Book book : bookList) {
                        sb.append(book.toString()).append("\n");
                    }
                    textview.setText(sb.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent();
        intent.setPackage("com.julian.service");
        intent.setAction("com.julian.ipcdemo.BookService");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {//insert
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.julian.service.BookContentProvider/Book");
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 10);
                contentValues.put("name", "冰与火之歌");
                getContentResolver().insert(uri, contentValues);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {//query
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.julian.service.BookContentProvider/Book");
                Cursor cursor = getContentResolver().query(uri, new String[]{"id", "name"}, null, null, null);
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    sb.append("id=").append(cursor.getInt(0)).append(",name=").append(cursor.getString(1)).append("\n");

                }
                cursor.close();
                textview.setText(sb.toString());
            }
        });
        findViewById(R.id.btn_clear_text).setOnClickListener(new View.OnClickListener() {//query
            @Override
            public void onClick(View v) {
                textview.setText("");
            }
        });


    }
}
