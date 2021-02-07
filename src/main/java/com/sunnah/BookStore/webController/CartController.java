package com.sunnah.BookStore.webController;

import com.sunnah.BookStore.business.service.CartService;
import com.sunnah.BookStore.data.Entity.BasketItem;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@SessionAttributes("cartItems")
public class CartController {

    @Autowired
    private final CartService cartService;

    @GetMapping("/cart")
    public String showCart(Model model) {

        model.addAttribute("cartItems", cartService.listBasket());

        return "cart/index";
    }

    @GetMapping("/cart/add/{bookId}")
    public String add(@PathVariable("bookId") long bookId,
                      Model model, Principal principal) {

        cartService.addItemToCart(bookId);

        cartService.listBookInCart().forEach(item -> {
            System.out.println(item.getId());
        });

        return "forward:/cart";
    }

    @GetMapping("/cart/delete/{bookId}")
    public String delete(@PathVariable("bookId") long bookId,
                         Model model) {
        cartService.deleteItemFromCart(bookId);

        return "forward:/cart";
    }

    @GetMapping("/cart/amount/{bookId}")
    public String addAmount(@RequestParam("amount") Integer amount,
                            @PathVariable("bookId") long bookId,
                            Model model) {

        if (amount > 10 || amount < 1) {
            amount = 1;
            model.addAttribute("ErrorAmount", "Insert amount between 1 and 10");
        }
        System.out.println("amount is for book : " + Integer.toString(amount));
        cartService.addAmountToItem(bookId, amount);

        return "forward:/cart";
    }
}
