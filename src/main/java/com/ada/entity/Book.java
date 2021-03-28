package com.ada.entity;

import com.ada.dto.BookDTO;
import com.ada.utils.DateToString;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    private String name;

    private String author;

    private String category;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate publishedDate;

    private String content;

    private boolean isAvailable=true;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    @JsonIgnore
    private Student student;

    public static Book of(BookDTO bookDTO){
        Book book = new Book();
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setPublishedDate(DateToString.stringToLocalDate(bookDTO.getPublishedDate()));
        book.setContent(bookDTO.getContent());
        book.setAvailable(bookDTO.getAvailable());
        return book;
    }


    public Book(String name,String author, LocalDate publishedDate, String content, boolean isAvailable,String category) {
        this.name =name;
        this.author = author;
        this.publishedDate = publishedDate;
        this.content = content;
        this.isAvailable = isAvailable;
        this.category =category;
    }

    public boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}
