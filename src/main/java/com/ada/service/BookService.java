package com.ada.service;

import com.ada.dto.BookDTO;
import com.ada.entity.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBookList();

    void addBook(BookDTO bookDTO);

    BookDTO findBookByName(String name);

    Book findBookByBookName(String name);

    BookDTO findBookByCategory(String category);

    BookDTO findByAuthor(String author);

    BookDTO findByNameAndCategoryAndAuthor(String name, String category, String author);

    BookDTO findByNameAndCategoryOrAuthor(String name, String category,String author);

    void pickUpBookFromLibrary(Long studentId, Long bookId);

    void dropOffBookFromLibrary(Long bookId);

    void updateAvailability(Boolean isAvailable,Long bookId);


}
