package com.sunnah.BookStore.data.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Auth_USER_GROUP_ID")
public class AuthGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "Auth_Group")
    private String authGroup;

    public AuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

    public AuthGroup(User user, String role) {
        this.user = user;
        this.authGroup = role;
    }
}
