package com.ada.controller;

import com.ada.dto.BookDTO;
import com.ada.dto.ResponseDTO;
import com.ada.service.BookServiceImpl;
import com.ada.service.SessionService;
import com.ada.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/book")
public class LibraryController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private StudentServiceImpl studentService;



    @GetMapping("/list")
    public List<? extends Object> getAllBooks(HttpServletRequest request){
        if(sessionService.getSessionInfo(request)!=null)
        return bookService.getAllBookList();
        return List.of(ResponseDTO.getInstance().error("Please login before hit the url"));
    }

    @PostMapping("/add")
    public ResponseDTO addBook(@RequestBody BookDTO bookDTO, HttpServletRequest request){
        if(sessionService.getSessionInfo(request)!=null) {
            bookService.addBook(bookDTO);
            return ResponseDTO.getInstance().success("Successfully added");
        }
        return ResponseDTO.getInstance().success("Please login before hit the url");
    }

    @GetMapping("/by/name/{name}")
    public Object getBookByName(@PathVariable("name") String name,HttpServletRequest request){
        if(sessionService.getSessionInfo(request)!=null)
        {
            return bookService.findBookByName(name);
        }
       return "You should login first before accessing the resources";

    }
    @GetMapping("/by/author/{author}")
    public Object getBookByAuthor(@PathVariable("author") String author,HttpServletRequest request){
        if(sessionService.getSessionInfo(request)!=null)
       return bookService.findByAuthor(author);
        return "You should login first before accessing the resources";
    }

    @GetMapping("/by/category/{category}")
    public Object getBookByCategory(@PathVariable("category") String category,HttpServletRequest request){
        if(sessionService.getSessionInfo(request)!=null)
        return bookService.findBookByCategory(category);
        return "You should login first before accessing the resources";
    }

    @GetMapping("/by/name/category/author/{name}/{category}/{author}")
    public Object getBookByNameAndCategoryAndAuthor(@PathVariable("name") String name, @PathVariable("category")  String category, @PathVariable(value = "author",required = false) String author,HttpServletRequest request){
        if (sessionService.getSessionInfo(request)==null)
            return "You should login first before accessing the resources";
        if(author!=null)
        return bookService.findByNameAndCategoryAndAuthor(name,category,author);
        else return bookService.findByNameAndCategoryOrAuthor(name,category,author);
    }

    @PostMapping("/add/userName/bookName/{userName}/{bookName}")
    public Object userPickUpBookFromLibrary(@PathVariable("userName") String userName, @PathVariable("bookName") String bookName,HttpServletRequest request){
        if (sessionService.getSessionInfo(request)==null)
            return "You should login first before accessing the resources";
       return studentService.pickUpBook(userName,bookName);
    }

    @PostMapping("/dropOff/userName/bookName/{userName}/{bookName}")
    public Object userDropOffBookFromLibrary(@PathVariable("userName") String userName, @PathVariable("bookName") String bookName,HttpServletRequest request){
        if (sessionService.getSessionInfo(request)==null)
            return "You should login first before accessing the resources";
        return studentService.dropOffBook(userName,bookName);
    }

    @GetMapping("/list/{userId}")
    public Object listOfBooksByLoggedInUser(@PathVariable("userId") Long userId,HttpServletRequest request){
        if (sessionService.getSessionInfo(request)==null)
            return "You should login first before accessing the resources";
       return studentService.listOfBooksFromLibraryByLoggedInUser(userId);
    }


}
