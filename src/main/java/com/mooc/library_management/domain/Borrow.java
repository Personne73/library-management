package com.mooc.library_management.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // Many Borrow records can be associated with one Book
    @JoinColumn(name = "book", nullable = false)
    private Book book;

    @ManyToOne // Many Borrow records can be associated with one User
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "borrow_date", nullable = false)
    private LocalDate borrowDate;
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "is_returned", nullable = false)
    private boolean isReturned;

    public Borrow() {}

    public Borrow(Book book, User user, LocalDate borrowDate, LocalDate returnDate, boolean isReturned) {
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.isReturned = isReturned;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }
}

