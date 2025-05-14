package com.mooc.library_management.repository;

import com.mooc.library_management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
