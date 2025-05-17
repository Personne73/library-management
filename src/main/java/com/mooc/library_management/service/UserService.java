package com.mooc.library_management.service;

import com.mooc.library_management.domain.Book;
import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.domain.User;
import com.mooc.library_management.exception.ResourceNotFoundException;
import com.mooc.library_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a new user
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    // Get all users
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    // Update a user by ID
    public User updateUserById(User user, Long id) {
        // Check if the user exists
        if (!this.userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", id);
        }

        User originalUser = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        // Update the user details
        if (user.getName() != null) originalUser.setName(user.getName());
        if (user.getEmail() != null) originalUser.setEmail(user.getEmail());

        return this.userRepository.save(originalUser);
    }

    // Delete a user by ID
    public void deleteUserById(Long id) {
        // Check if the user exists
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        for (Borrow borrow : user.getBorrows()) {
            Book book = borrow.getBook();
            if (book != null) {
                book.getBorrows().remove(borrow);
            }
        }

        userRepository.delete(user);
    }
}
