package com.sunnah.BookStore.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String showCart(Principal principal) {

        System.out.println("HI, " + principal.getName());

        return "cart";
    }
}
