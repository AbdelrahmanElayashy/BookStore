package com.sunnah.BookStore.webController;

import com.sunnah.BookStore.business.domain.LoginDto;
import com.sunnah.BookStore.data.Entity.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModelAttribute {

    @ModelAttribute("user")
    public User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("loginUser")
    public LoginDto getDefaultLogin() {
        return new LoginDto();
    }
}
