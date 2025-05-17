package com.mooc.library_management.service;

import com.mooc.library_management.domain.Book;
import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.domain.User;
import com.mooc.library_management.exception.ResourceNotFoundException;
import com.mooc.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add a new book
    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    // Get a book by ID
    public Book getBookById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));
    }

    // Update a book by ID
    public Book updateBookById(Book book, Long id) {
        // Check if the book exists
        if (!this.bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", id);
        }

        Book originalBook = this.bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));

        // Update the book details
        if (book.getTitle() != null) originalBook.setTitle(book.getTitle());
        if (book.getAuthor() != null) originalBook.setAuthor(book.getAuthor());
        if (book.getPublisher() != null) originalBook.setPublisher(book.getPublisher());
        if (book.getPublicationYear() != null) originalBook.setPublicationYear(book.getPublicationYear());
        if (book.getGenre() != null) originalBook.setGenre(book.getGenre());
        if (book.getSummary() != null) originalBook.setSummary(book.getSummary());
        if (book.getIsbn() != null) originalBook.setIsbn(book.getIsbn());

        boolean isAnyBorrowed = originalBook.getBorrows().stream()
                .anyMatch(borrow -> !borrow.isReturned());

        originalBook.setBorrowed(isAnyBorrowed);

        return this.bookRepository.save(originalBook);
    }

    // Delete a book by ID
    public void deleteBookById(Long id) {
        // Check if the book exists
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", id));

        for (Borrow borrow : book.getBorrows()) {
            User user = borrow.getUser();
            if (user != null) {
                user.getBorrows().remove(borrow);
            }
        }

        this.bookRepository.delete(book);
    }
}
