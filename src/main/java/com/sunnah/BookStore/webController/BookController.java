package com.sunnah.BookStore.webController;


import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/book")
    public Callable<String> book(Model model, Principal principal) {

        return () -> {
            model.addAttribute("Books", bookRepository.findAll());
           // books.listIterator().forEachRemaining(System.out::println);
            return "book";
        };
    }


}
