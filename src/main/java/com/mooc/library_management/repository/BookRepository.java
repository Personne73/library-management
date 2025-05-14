package com.mooc.library_management.repository;

import com.mooc.library_management.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Custom query methods can be defined here if needed
    // Default methods provided by JpaRepository are sufficient for basic CRUD operations : save, findAll, findById, deleteById
}
