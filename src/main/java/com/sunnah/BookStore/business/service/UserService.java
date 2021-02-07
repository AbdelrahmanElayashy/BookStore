package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.business.domain.Dtos.UserDto;
import com.sunnah.BookStore.business.domain.LoginDto;
import com.sunnah.BookStore.data.Entity.Token;
import com.sunnah.BookStore.data.Entity.User;
import com.sunnah.BookStore.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service("customUserDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthGroupService authGroupService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, TokenService tokenService, AuthGroupService authGroupService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.authGroupService = authGroupService;

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
        //    user.setRole(Role.USER);
        authGroupService.addAuthGroupUser(user, "USER");
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

    public boolean checkLogin(LoginDto login, BindingResult result) {

        UserDetails user = this.loadUserByUsername(login.getEmail());
        if (user == null) {
            result.rejectValue("email", "error.user", "*Email is not found");
            return false;
        }

        if (!bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword())) {
            result.rejectValue("password", "error.user", "*Password is not correct");
            return false;
        }

        return true;
    }

    public boolean confirmCheckout(String email, UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            return false;
        User _user = user.get();
        _user.setFirstName(userDto.getFirstName());
        _user.setLastName(userDto.getLastName());
        _user.setHomeNumber(userDto.getHomeNumber());
        _user.setStreet(userDto.getStreet());
        _user.setContactEmail(userDto.getContactEmail());
        _user.setZip(userDto.getZip());
        _user.setState(userDto.getState());

        return true;
    }
}
