package com.sunnah.BookStore.webController;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdmin() {
        return "/admin/index";
    }

    @GetMapping("/admin/book/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddBook() {
        return "/admin/addBook";
    }


}
