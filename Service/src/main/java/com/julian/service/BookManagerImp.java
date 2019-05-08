package com.julian.service;

import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Julian on 2019/5/7.
 * Description:
 */
public class BookManagerImp extends BookManager.Stub {

    CopyOnWriteArrayList<Book> copyOnWriteArrayList = new CopyOnWriteArrayList<Book>();

    @Override
    public List<Book> getBookList() throws RemoteException {
        return copyOnWriteArrayList;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        copyOnWriteArrayList.add(book);
    }

    public CopyOnWriteArrayList<Book> getCopyOnWriteArrayList() {
        return copyOnWriteArrayList;
    }
}
