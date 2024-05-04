package com.bella.bouche.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, referencedColumnName = "id")
    private Account account;

    public String format() {
        return this.createdAt.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
    }

    public Post(String title, String content, LocalDateTime createdAt, Account account) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.account = account;
    }
}
