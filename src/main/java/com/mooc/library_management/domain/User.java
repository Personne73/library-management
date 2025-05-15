package com.mooc.library_management.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToMany(mappedBy = "user") // One User can have many Borrow records
    @Column(name = "borrows", nullable = false)
    @JsonManagedReference // to avoid infinite recursion
    private List<Borrow> borrows = new ArrayList<>();

    public User() {} // required by JPA

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    } // constructor

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Borrow> getBorrows() {
        return this.borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
