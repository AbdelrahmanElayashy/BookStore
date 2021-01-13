package com.sunnah.BookStore.WebController;

import com.sunnah.BookStore.business.domain.Login;
import com.sunnah.BookStore.business.service.UserService;
import com.sunnah.BookStore.data.Entity.User;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final UserService userService;

    @GetMapping("/login")
    public String loginGet() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(null == authentication || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        System.out.println("in cart");
        return "index";
    }

    @GetMapping("/login-error")
    public String loginError() {

        return "login-error";
    }


    @GetMapping("/register")
    public String register() {
        System.out.println("in register");
        return "registration";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String signUp(@Validated @ModelAttribute("user") User user,
                  BindingResult result, Model model) {
        System.out.println("in sign up");
        if(userService.ValidUserInput(user, result)) {
            System.out.println("in sign up serv");
            userService.signUpUser(user);
            model.addAttribute("dataSaved","Data successful saved");
            return "login";
        }
        System.out.println("in regis up");
        return "registration";

    }
}
