package com.example.bookApp.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;
    private String status;
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
