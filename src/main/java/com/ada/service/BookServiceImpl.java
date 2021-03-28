package com.ada.service;

import com.ada.dto.BookDTO;
import com.ada.entity.Book;
import com.ada.exception.CustomNotFoundException;
import com.ada.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookDTO> getAllBookList(){
          Iterable<Book> books = bookRepository.findAll();
          List<Book> bookList = new LinkedList<>();
          for (Book book: books){
              bookList.add(book);
          }


         return bookList.stream()
                  .map(BookDTO::from)
                  .collect(Collectors.toList());

    }

    @Override
    public void addBook(BookDTO bookDTO) {
       Book book = Book.of(bookDTO);
      bookRepository.save(book);
    }

    @Override
    public BookDTO findBookByName(String name){
       return bookRepository.findByName(name)
               .map(BookDTO::from)
               .orElseThrow(()->new CustomNotFoundException("There is no book by given name -> "+name));
    }

    @Override
    public Book findBookByBookName(String name){
        return bookRepository.findByName(name)
                .orElseThrow(()->new CustomNotFoundException("There is no book by given name -> "+name));
    }


    @Override
    public BookDTO findBookByCategory(String category){
        return bookRepository.findByCategory(category)
                .map(BookDTO::from)
                .orElseThrow(()->new CustomNotFoundException("There is no book by given category-> "+category));
    }

    @Override
    public BookDTO findByAuthor(String author){
       return bookRepository.findByAuthor(author)
               .map(BookDTO::from)
               .orElseThrow(()->new CustomNotFoundException("There is no book by given author -> "+author));
    }

    @Override
    public BookDTO findByNameAndCategoryAndAuthor(String name, String category, String author){
        return bookRepository.findByNameAndCategoryAndAuthor(name,category,author)
                .map(BookDTO::from)
                .orElseThrow(()->new CustomNotFoundException("There is no book for given parameters"));
    }

    @Override
    public BookDTO findByNameAndCategoryOrAuthor(String name,String category,String author){
      return  bookRepository.findByNameAndCategoryOrAuthor(name,category,author)
              .map(BookDTO::from)
              .orElseThrow(()->new CustomNotFoundException("There is no book for given parameters"));
    }

    @Override
    public void pickUpBookFromLibrary(Long studentId, Long bookId) {
        bookRepository.pickUpBook(studentId,bookId);
    }

    @Override
    public void dropOffBookFromLibrary(Long bookId) {
        bookRepository.dropOffBook(bookId);
    }

    @Override
    public void updateAvailability(Boolean isAvailable, Long bookId) {
        bookRepository.updateAvailability(isAvailable,bookId);
    }

}
