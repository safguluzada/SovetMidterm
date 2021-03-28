package com.ada.repository;

import com.ada.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {

  Optional<Book> findByName(String bookName);
  Optional<Book> findByAuthor(String author);
  Optional<Book> findByCategory(String category);
  Optional<Book> findByNameAndCategoryAndAuthor(String name,String category,String author);
  Optional<Book> findByNameAndCategoryOrAuthor(String name, String category, String author);

  @Transactional
  @Modifying
  @Query(value = "update books set student_id = ?1 where id =?2 ",nativeQuery = true)
  void pickUpBook(Long studentId, Long bookId);

  @Transactional
  @Modifying
  @Query(value = "update books set student_id =null where id =?1", nativeQuery = true)
  void dropOffBook(Long bookId);

  @Transactional
  @Modifying
  @Query(value = "update books set is_available = ?1 where id =?2", nativeQuery = true)
  void updateAvailability(Boolean isAvailable, Long bookId);

}
