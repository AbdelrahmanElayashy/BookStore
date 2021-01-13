package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.business.domain.Login;
import com.sunnah.BookStore.data.Entity.Role;
import com.sunnah.BookStore.data.Entity.Token;
import com.sunnah.BookStore.data.Entity.User;
import com.sunnah.BookStore.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.MessageFormat;
import java.util.Optional;

@Service("customUserDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByEmail(email);
        //System.out.println("HI :********" + user.get().getUsername());

        if(user.isEmpty()) {
            System.out.println("In load Exception************");
            throw new UsernameNotFoundException("Email or Password is incorrect!");
        }

        return user.get();
    }

    public boolean signUpUser(User user) {


        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(Role.USER);
        userRepository.save(user);
        tokenService.saveToken(new Token(user));
        return true;
    }


    public void confirmUser(Token confirmationToken) {

        final User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
        tokenService.deleteToken(confirmationToken.getId());

    }

    private boolean confirmPassword(String pass1, String pass2) {

        return pass1.equals(pass2);
    }

    public boolean ValidUserInput(User user, BindingResult result) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "*Email is already exists");
            return false;
        }


        System.out.println("user Rep");


        if (result.hasErrors()) {
            return false;
        }
        System.out.println("user Rep");
        if (!this.confirmPassword(user.getPassword(), user.getPasswordConfirmed())) {
            result.rejectValue("passwordConfirmed", "error.user", "*Password does not match");
            return false;
        }


        return true;
    }

    public boolean checkLogin(Login login, BindingResult result) {

       UserDetails user = this.loadUserByUsername(login.getEmail());
       if(user == null) {
           result.rejectValue("email", "error.user", "*Email is not found");
           return false;
       }

       if(!bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword())) {
           result.rejectValue("password", "error.user", "*Password is not correct");
           return false;
       }

       return true;
    }
}
