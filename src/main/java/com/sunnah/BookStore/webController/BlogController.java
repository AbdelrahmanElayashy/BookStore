package com.sunnah.BookStore.webController;

import com.sunnah.BookStore.data.Entity.Post;
import com.sunnah.BookStore.data.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
public class BlogController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/blog")
    private Callable<String> blog(Model model) {
        return () -> {
            List<Post> posts = new ArrayList<>();
            Iterable<Post> iter = postRepository.findAll();
            iter.forEach(post ->{ posts.add(post); });
            model.addAttribute("posts", posts);
         //   model.addAttribute("post",postRepository.findById((long) 1).get());
            posts.listIterator().forEachRemaining(System.out::println);
            return "blog";
        };
    }
}
