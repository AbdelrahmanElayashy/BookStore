package com.sunnah.BookStore.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getRoot() {
        return "index";
    }

    @GetMapping("/index")
    public String getHome() {
        return "index";
    }

}
