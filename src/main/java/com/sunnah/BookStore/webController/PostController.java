package com.sunnah.BookStore.webController;

import com.sunnah.BookStore.data.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("/post/{id}")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public Callable<String> getPost(@PathVariable(value="id") long id,
                                    Model model) {
        System.out.println("i am in getPost" + id);
        return () -> {
            model.addAttribute("post", postRepository.findById(id).get());
            return "post";
        };
    }
}
