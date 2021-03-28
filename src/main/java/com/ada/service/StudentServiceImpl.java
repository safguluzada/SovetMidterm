package com.ada.service;

import com.ada.dto.ResponseDTO;
import com.ada.dto.StudentDTO;
import com.ada.entity.Book;
import com.ada.entity.Student;
import com.ada.exception.CustomNotFoundException;
import com.ada.repository.StudentRepository;
import com.ada.utils.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BookServiceImpl bookService;

    @Override
    public Student save(Student student){
        Student newStudent = new Student(student.getFirstName(), student.getLastName(), student.getEmail(), EncryptPassword.encryptPassword(student.getPassword()), student.getToken(), student.getTokenValidated());
     return  studentRepository.save(newStudent);
    }

    @Override
    public Optional<Student> login(String email, String password)  {
       Optional<Student> user = studentRepository.findByEmailAndPassword(email,EncryptPassword.encryptPassword(password));
       return user;

    }

    @Override
    public ResponseDTO pickUpBook(String userName, String bookName) {
        Student student =  studentRepository.findByFirstName(userName).orElseThrow(()->new CustomNotFoundException("There is no user by given name "+userName));
        Book book =  bookService.findBookByBookName(bookName);
        if (book.getAvailable()==true) {
            bookService.pickUpBookFromLibrary(student.getId(), book.getId());
            bookService.updateAvailability(false, book.getId());
          return   ResponseDTO.getInstance().success("added Successfully");
        }
      return   ResponseDTO.getInstance().error("This book is taken by "+ book.getStudent().getFirstName() +" " + book.getStudent().getLastName() + " therefore is not available");
    }


    @Override
    public ResponseDTO dropOffBook(String userName, String bookName) {
        Book book =  bookService.findBookByBookName(bookName);

        bookService.dropOffBookFromLibrary(book.getId());
        bookService.updateAvailability(true,book.getId());
        return ResponseDTO.getInstance().success(userName + " has dropped "+ bookName + " successfully");
    }

    @Override
    public StudentDTO listOfBooksFromLibraryByLoggedInUser(Long userId) {
        return studentRepository.findById(userId)
                .map(StudentDTO::from)
                 .orElseThrow(()->new CustomNotFoundException("There is no user by given id " + userId));
    }

    @Override
    public void updateToken(Boolean token){
        studentRepository.updateIsTokenValidated(token);
    }
}
