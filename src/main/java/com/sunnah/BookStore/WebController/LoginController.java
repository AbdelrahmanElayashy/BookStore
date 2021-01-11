package com.sunnah.BookStore.WebController;

import com.sunnah.BookStore.business.domain.Login;
import com.sunnah.BookStore.business.service.UserService;
import com.sunnah.BookStore.data.Entity.User;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginGet() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("in login");
        if(null == authentication || authentication instanceof AnonymousAuthenticationToken)
            return "login";
        System.out.println("in cart");
        return "forward:/cart";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUser") Login login,
                        BindingResult result){

        if(!userService.checkLogin(login, result)) {
            return "login";
        }

        return "cart";
    }

    @GetMapping("/register")
    public String register() {
        System.out.println("in register");
        return "registration";
    }

    @PostMapping("/sign-up")
    public String signUp(@Validated @ModelAttribute("user") User user,
                  BindingResult result, Model model) {

        if(userService.ValidUserInput(user, result)) {
            userService.signUpUser(user);
            model.addAttribute("dataSaved","Data successful saved");
            return "login";
        }

        return "registration";

    }
}
