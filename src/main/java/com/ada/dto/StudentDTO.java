package com.ada.dto;

import com.ada.entity.Book;
import com.ada.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String firstName;

    private String lastName;

    private String email;

    private List<Book> bookList;


    public static StudentDTO from(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setBookList(student.getBookList());
        return studentDTO;
    }

}
