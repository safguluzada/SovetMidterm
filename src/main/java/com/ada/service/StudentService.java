package com.ada.service;


import com.ada.dto.ResponseDTO;
import com.ada.dto.StudentDTO;
import com.ada.entity.Student;

import java.util.Optional;

public interface StudentService {

    Student save(Student student) throws Exception;
    void updateToken(Boolean token);
    Optional<Student> login(String email, String password);
    ResponseDTO pickUpBook(String userName, String bookName);
    ResponseDTO dropOffBook(String userName, String bookName);
    StudentDTO listOfBooksFromLibraryByLoggedInUser(Long userId);

}
