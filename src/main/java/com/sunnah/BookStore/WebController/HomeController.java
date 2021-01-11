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

    @GetMapping("/goToSearch")
    public String goToSearch() {
        return "search";
    }

    @GetMapping("/goToBook")
    public String goToBook() {
        return "forward:/book";
    }

    @GetMapping("/goToBlog")
    public String goToBlog(){
        return "forward:/blog";
    }

    @GetMapping("/contact")
    public String contact(){return "cart";};

    @GetMapping("/goToLogin")
    public String goToLogin(){

        return "forward:/login";
    }

    @GetMapping("/goToCart")
    public String goToCart() {
        return "forward:/cart";
    }
}
