package com.bella.bouche.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String jobPosition;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    public String format() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    }

    public Account(String userName, String password, String email, String firstName, String lastName, String bio, String jobPosition, String company, LocalDateTime createdAt) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.jobPosition = jobPosition;
        this.company = company;
        this.createdAt = createdAt;
    }
}
