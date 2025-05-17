package com.mooc.library_management.init;

import com.mooc.library_management.domain.Book;
import com.mooc.library_management.domain.Borrow;
import com.mooc.library_management.domain.User;
import com.mooc.library_management.repository.BookRepository;
import com.mooc.library_management.repository.BorrowRepository;
import com.mooc.library_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BorrowRepository borrowRepository;

    public DataInitializer(BookRepository bookRepository, UserRepository userRepository, BorrowRepository borrowRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.borrowRepository = borrowRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        borrowRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();

        // Books mock data
        Book book1 = new Book("1984", "George Orwell", "Secker & Warburg", 1949, "Dystopia",
                "Classic dystopian novel", "1234567890");
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", "Allen & Unwin", 1937, "Fantasy",
                "Fantasy adventure", "0987654321");
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co.", 1960, "Fiction",
                "Classic novel", "1111111111");
        Book book4 = new Book("Pride and Prejudice", "Jane Austen", "T. Egerton", 1813, "Romance",
                "Romantic novel", "2222222222");
        Book book5 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons", 1925, "Fiction",
                "Tragedy novel", "3333333333");

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);

        // Users mock data
        User user1 = new User("Alice Dupont", "alice@example.com");
        User user2 = new User("Bob Martin", "bob@example.com");

        userRepository.save(user1);
        userRepository.save(user2);

        // Borrows mock data
        // Borrow not returned
        Borrow borrow1 = new Borrow(
                book1,
                user1,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 15),
                false
        );

        // Borrow already returned
        Borrow borrow2 = new Borrow(
                book2,
                user2,
                LocalDate.of(2025, 4, 20),
                LocalDate.of(2025, 5, 5),
                true
        );

        borrowRepository.save(borrow1);
        borrowRepository.save(borrow2);

        book1.getBorrows().add(borrow1);
        book1.setBorrowed(true);
        book2.getBorrows().add(borrow2);
        bookRepository.save(book1);
        bookRepository.save(book2);

        user1.getBorrows().add(borrow1);
        user2.getBorrows().add(borrow2);
        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("[INFO] Mock data successfully loaded in database !");
    }
}
