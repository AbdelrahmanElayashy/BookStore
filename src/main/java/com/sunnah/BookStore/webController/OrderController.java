package com.sunnah.BookStore.webController;

import com.sunnah.BookStore.business.domain.Dtos.UserDto;
import com.sunnah.BookStore.business.service.CartService;
import com.sunnah.BookStore.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping("/checkout")
    private String checkout(Model model) {

        if (cartService.listBasket().isEmpty()) {
            model.addAttribute("EmptyCart", true);
            return "forward:/cart";
        }

        return "cart/checkout";
    }

    @PostMapping("/checkout/confirm")
    private String confirmCheckout(@Validated @ModelAttribute("userInfo") UserDto userDto,
                                   Principal principal) {
        return "";
    }
}
