package com.sunnah.BookStore.WebController;

import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.concurrent.Callable;

@Controller
public class SearchController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/search")
    public Callable<String> search(@RequestParam("search") String search, Model model){

        Optional<Book> books = bookRepository.findByName("search");
        model.addAttribute("books", books);
        return () -> {
            return "search";
        };
    }
}
