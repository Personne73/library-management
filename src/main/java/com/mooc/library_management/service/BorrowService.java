package com.mooc.library_management.service;

import com.mooc.library_management.domain.Book;
import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.exception.ResourceNotFoundException;
import com.mooc.library_management.repository.BookRepository;
import com.mooc.library_management.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;

    public BorrowService(BorrowRepository borrowRepository, BookRepository bookRepository) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
    }

    // Create a new borrow record
    public Borrow createBorrow(Borrow borrow) {
        //return this.borrowRepository.save(borrow);
        if (!borrow.getBook().isBorrowed()) {
            borrow.getBook().setBorrowed(true);
        }

        Borrow createdBorrow = this.borrowRepository.save(borrow);
        Book book = borrow.getBook();
        book.getBorrows().add(createdBorrow);
        this.bookRepository.save(book);

        return createdBorrow;
    }

    // Get all borrow records
    public List<Borrow> getAllBorrows() {
        return this.borrowRepository.findAll();
    }

    // Get a borrow record by ID
    public Borrow getBorrowById(Long id) {
        return this.borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow", id));
    }

    // Update a borrow record by ID
    public Borrow updateBorrowById(Borrow borrow, Long id) {
        // Check if the borrow record exists
        if (!this.borrowRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrow", id);
        }

        Borrow originalBorrow = this.borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow", id));

        // Update the borrow record details
        if (borrow.getUser() != null) originalBorrow.setUser(borrow.getUser());
        if (borrow.getBook() != null) originalBorrow.setBook(borrow.getBook());
        if (borrow.getBorrowDate() != null) originalBorrow.setBorrowDate(borrow.getBorrowDate());
        if (borrow.getReturnDate() != null) originalBorrow.setReturnDate(borrow.getReturnDate());
        if (borrow.isReturned()) originalBorrow.setReturned(borrow.isReturned());

        return this.borrowRepository.save(originalBorrow);
    }

    // Delete a borrow record by ID
    public void deleteBorrowById(Long id) {
        // Check if the borrow record exists
        Borrow borrow = this.borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow", id));

        // Remove the borrow record from the book and user
        if (!borrow.isReturned()) {
            borrow.getBook().setBorrowed(false);  // Mark book as available again
            bookRepository.save(borrow.getBook());
        }
        if (borrow.getBook() != null) {
            borrow.getBook().getBorrows().remove(borrow);
        }
        if (borrow.getUser() != null) {
            borrow.getUser().getBorrows().remove(borrow);
        }

        this.borrowRepository.delete(borrow);
    }
}
