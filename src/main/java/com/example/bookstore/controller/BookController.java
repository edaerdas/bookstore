package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookStockDto;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody BookDto bookDto) {

        Long bookId = bookService.createBook(bookDto);
        if (bookId != 0L) {
            String message = String.format("Book saved successfully, id : %s", bookId);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Book cannot save to database", HttpStatus.CONFLICT);
    }

    @PostMapping("/updateStock")
    public ResponseEntity<String> updateStock(@RequestBody BookStockDto bookStockDto) {
        HttpStatus httpStatus = bookService.updateStock(bookStockDto);
        if (httpStatus.equals(HttpStatus.OK))
            return new ResponseEntity<String>("Book's stock updated successfully", httpStatus);

        return new ResponseEntity<>("Book's stock cannot updated.", httpStatus);
    }
}
