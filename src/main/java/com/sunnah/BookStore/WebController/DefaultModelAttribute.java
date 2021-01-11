package com.sunnah.BookStore.WebController;

import com.sunnah.BookStore.business.domain.Login;
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
    public Login getDefaultLogin() {return new Login();}
}
