// BookManager.aidl
package com.julian.service;

// Declare any non-default types here with import statements
import com.julian.service.Book;
interface BookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
