package com.mooc.library_management.service;

import com.mooc.library_management.domain.Book;
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
        if (originalBook.getTitle() != null) originalBook.setTitle(book.getTitle());
        if (originalBook.getAuthor() != null) originalBook.setAuthor(book.getAuthor());
        if (originalBook.getPublisher() != null) originalBook.setPublisher(book.getPublisher());
        if (originalBook.getPublicationYear() != null) originalBook.setPublicationYear(book.getPublicationYear());
        if (originalBook.getGenre() != null) originalBook.setGenre(book.getGenre());
        if (originalBook.getSummary() != null) originalBook.setSummary(book.getSummary());
        if (originalBook.getIsbn() != null) originalBook.setIsbn(book.getIsbn());
        if (originalBook.isBorrowed()) originalBook.setBorrowed(book.isBorrowed());

        return this.bookRepository.save(originalBook);
    }

    // Delete a book by ID
    public void deleteBookById(Long id) {
        // Check if the book exists
        if (!this.bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", id);
        }
        this.bookRepository.deleteById(id);
    }
}
