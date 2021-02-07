package com.sunnah.BookStore.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getRoot() {
        return "forward:/index";
    }

    @GetMapping("/index")
    public String getHome() {
        return "index";
    }


}
