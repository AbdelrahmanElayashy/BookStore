package com.sunnah.BookStore.data.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import java.util.Collection;
import java.util.Collections;


@Entity
@Data
@NoArgsConstructor
@Table(name = "`USER`")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

/*    @Column(name = "username", unique = true)
    @NotEmpty(message = "*Please provide username")
    @Length(min = 5, message = "*Your username must have at least 5 characters")
    private String username;*/

    @Column(name = "email", unique = true, nullable = false)
    @Email
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "*Please give password")
    private String password;

    @Transient
    private String passwordConfirmed;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(columnDefinition = "boolean default false")
    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    private Basket basket;



    public User(@Email String email, @Pattern(regexp = "((?=.*[A-Z]).{6,10})", message = "*Password must have one upper case, one lower case and should be between 6 and 10 characters") String password, String passwordConfirmed, Role role, Boolean enabled) {
        this.email = email;
        this.password = password;
        this.passwordConfirmed = passwordConfirmed;
        this.role = role;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final SimpleGrantedAuthority auth = new SimpleGrantedAuthority(Role.USER.name());
        return Collections.singleton(auth);

    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}