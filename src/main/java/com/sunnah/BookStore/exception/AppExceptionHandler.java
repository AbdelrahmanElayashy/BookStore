package com.sunnah.BookStore.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleExceptionLogin(UsernameNotFoundException ex, Model model) {

        String er = ex.getMessage();
        model.addAttribute("errorLogin", er);
        System.out.println("In Exception *********");
        return "login";
    }
}
