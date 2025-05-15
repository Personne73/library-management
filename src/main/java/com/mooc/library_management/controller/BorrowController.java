package com.mooc.library_management.controller;

import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.service.BorrowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<Borrow> borrow(@RequestBody Borrow borrow) {
        Borrow createdBorrow = this.borrowService.createBorrow(borrow);
        return ResponseEntity.status(201).body(createdBorrow);
    }

    @GetMapping
    public ResponseEntity<List<Borrow>> getAllBorrows() {
        List<Borrow> borrows = this.borrowService.getAllBorrows();
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrow> getBorrowById(@PathVariable Long id) {
        Borrow borrow = this.borrowService.getBorrowById(id);
        return ResponseEntity.ok(borrow);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Borrow> updateBorrowById(@PathVariable Long id, @RequestBody Borrow borrow) {
        Borrow updatedBorrow = this.borrowService.updateBorrowById(borrow, id);
        return ResponseEntity.ok(updatedBorrow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowById(@PathVariable Long id) {
        this.borrowService.deleteBorrowById(id);
        return ResponseEntity.noContent().build();
    }
}
