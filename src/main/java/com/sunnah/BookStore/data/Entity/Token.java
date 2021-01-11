package com.sunnah.BookStore.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationToken;

    private LocalDate createdDate;

    @OneToOne(targetEntity = User.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    public Token(User user) {
        this.user = user;
        this.createdDate = LocalDate.now();
        confirmationToken = UUID.randomUUID().toString();
    }
}