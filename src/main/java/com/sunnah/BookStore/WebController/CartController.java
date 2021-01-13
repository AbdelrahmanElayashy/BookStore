package com.sunnah.BookStore.WebController;

import com.sunnah.BookStore.business.service.CartService;
import com.sunnah.BookStore.data.Entity.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import java.security.Principal;

@Controller
@AllArgsConstructor
@SessionAttributes("cartItems")
public class CartController {

    @Autowired
    private final CartService cartService;

    @GetMapping("/cart")
    public String showCart(Principal principal) {

        System.out.println("HI, " + principal.getName());

        return "cart";
    }

    @GetMapping("/cart/add/{bookId}")
    public String add(@PathVariable("bookId") long bookId, Model model, Principal principal) {
        System.out.println("List Baskets from user : " + principal.getName());
        cartService.addItemToCart(bookId);

        cartService.listBookInCart().forEach(item -> {
            System.out.println(item.getId());
        });

        model.addAttribute("cartItems", cartService.listBookInCart());
        return "forward:/book";
    }

    @GetMapping("/cart/delete/{bookId}")
    public String add(@PathVariable("bookId") long bookId, Model model) {
        cartService.deleteItemFromCart(bookId);
        model.addAttribute("cartItems", cartService.listBookInCart());

        return "forward:/cart";
    }
}
