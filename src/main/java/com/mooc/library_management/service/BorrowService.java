package com.mooc.library_management.service;

import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.exception.ResourceNotFoundException;
import com.mooc.library_management.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // Create a new borrow record
    public Borrow createBorrow(Borrow borrow) {
        return this.borrowRepository.save(borrow);
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
        if (!this.borrowRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrow", id);
        }
        this.borrowRepository.deleteById(id);
    }
}
