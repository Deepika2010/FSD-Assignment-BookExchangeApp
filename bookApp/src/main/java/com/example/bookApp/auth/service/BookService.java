package com.example.bookApp.auth.service;

import com.example.bookApp.auth.model.Book;
import com.example.bookApp.auth.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> searchBooks(String title, String genre, String status) {
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }
        if (genre != null && !genre.isEmpty()) {
            return bookRepository.findByGenreContainingIgnoreCase(genre);
        }
        if (status != null && !status.isEmpty()) {
            return bookRepository.findByStatus(status);
        }
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
