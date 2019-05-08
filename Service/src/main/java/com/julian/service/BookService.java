package com.julian.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Julian on 2019/5/7.
 * Description:
 */
public class BookService extends Service {

    private BookManagerImp bookManagerImp = new BookManagerImp();

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        CopyOnWriteArrayList<Book> copyOnWriteArrayList = bookManagerImp.getCopyOnWriteArrayList();
        copyOnWriteArrayList.add(new Book("1"));
        copyOnWriteArrayList.add(new Book("2"));
        copyOnWriteArrayList.add(new Book("3"));
        copyOnWriteArrayList.add(new Book("4"));
        copyOnWriteArrayList.add(new Book("5"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return bookManagerImp;
    }
}
