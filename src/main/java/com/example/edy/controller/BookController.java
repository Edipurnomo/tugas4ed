package com.example.edy.controller;

import com.example.edy.model.Book;
import com.example.edy.model.BookCategory;
import com.example.edy.repository.BookRepository;
import com.example.edy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    List<Book> getAllBooks(){return  bookRepository.findAll();}

    @PostMapping("/insert")
    public Map<String,Object> addNewBook(@RequestBody Book body){
        Map<String ,Object> result = new HashMap<>();
        if (bookService.saveBook(body)){
            result.put("success", true);
            result.put("message","book berhasil ditambahkan");
        }else {
            result.put("successs",false);
            result.put("message","book gagal ditambahkan");
        }
        return result;
    }

    @DeleteMapping("/delete")
        //id dr param postman
    Map<String,Object>deleteBook(@RequestParam int id){
        Map<String,Object> result = new HashMap<>();
        if (bookService.DeleteBook(id)) {
            result.put("success", true);
            result.put("message", "Book Deleted!");
        } else{
            result.put("success", false);
            result.put("message", "Book Not Deleted!");
        } return result;
    }

    @PutMapping("/update")
    Map<String,Object>updateBook(@RequestBody Book body){
        System.out.println("body : " + body.toString());
        Map<String,Object> result = new HashMap<>();

        if (bookService.UpdateBook(body)) {
            result.put("success", true);
            result.put("message", "Book Updated!");
        } else{
            result.put("success", false);
            result.put("message", "Book Not Updated!");
        }
        return result;
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String ,Object>> getAllBooks(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size )
    { return bookService.getAllTitle(search,page,size); }

    @GetMapping("/byTitle")
    public List<Book> getUsersByTitle(@RequestParam(required = false)String title)
    { return bookService.getAllBookByTitle(title); }

    @GetMapping("/byCategory")
    public List<Book> getUsersByCategory(@RequestParam(required = false)int id )
    { return bookService.getAllBookByCategory(id);

    }

}


