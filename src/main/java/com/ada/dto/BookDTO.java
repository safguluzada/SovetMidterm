package com.ada.dto;

import com.ada.entity.Book;
import com.ada.utils.DateToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {


    private String name;

    private String author;

    private String category;

    private String publishedDate;

    private String content;

    private boolean isAvailable=true;

    private String takenBy;


    public static BookDTO from(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setPublishedDate(DateToString.dateToString(book.getPublishedDate()));
        bookDTO.setContent(book.getContent());
        bookDTO.setAvailable(book.getAvailable());
        if (book.getAvailable()==false) {
            bookDTO.setTakenBy(book.getStudent().getFirstName());
        }
        else
            bookDTO.setTakenBy("Nobody");
        return bookDTO;
    }


    public boolean getAvailable() {
        return isAvailable;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}
