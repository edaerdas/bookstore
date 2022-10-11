package com.example.bookstore.service;

import com.example.bookstore.database.entity.Book;
import com.example.bookstore.database.repository.BookRepository;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookStockDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public Long createBook(BookDto bookDto) {
        try {
            Book book = new Book(bookDto.name, bookDto.stockQuantity, bookDto.amount);
            Book bookEntity = bookRepository.save(book);
            return bookEntity.getId();
        } catch (Exception e) {
            log.error("Book cannot save to database, book {}", bookDto);
            e.printStackTrace();
        }
        return 0L;
    }

    public HttpStatus updateStock(BookStockDto bookStockDto) {

        Optional<Book> bookOptional = bookRepository.findById(bookStockDto.bookId);
        if (!bookOptional.isPresent())
            return HttpStatus.NOT_FOUND;

        Book book = bookOptional.get();
        book.setStockQuantity(bookStockDto.stockQuantity);
        bookRepository.save(book);
        return HttpStatus.OK;
    }
}
