package com.mooc.library_management.repository;

import com.mooc.library_management.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow,Integer> { }
