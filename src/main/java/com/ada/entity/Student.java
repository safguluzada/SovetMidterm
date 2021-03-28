package com.ada.entity;

import com.ada.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Long  id;

    @Column(name = "first_name")
    private String firstName;

    private String lastName;

    private String token;

    private Boolean isTokenValidated;

    private String email;

    private String password;


    @OneToMany(targetEntity = Book.class, mappedBy = "student",cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Student(String firstName, String lastName, String email, String password, String token, Boolean isTokenValidated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token=token;
        this.isTokenValidated=isTokenValidated;
    }

    public static Student of(StudentDTO studentDTO){
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setBookList(studentDTO.getBookList());
        return student;
    }


    public Boolean getTokenValidated() {
        return isTokenValidated;
    }

    public void setTokenValidated(Boolean tokenValidated) {
        isTokenValidated = tokenValidated;
    }
}
