package com.sunnah.BookStore;

import com.sunnah.BookStore.business.domain.Categorie;
import com.sunnah.BookStore.business.domain.Language;
import com.sunnah.BookStore.business.service.UserService;
import com.sunnah.BookStore.data.Entity.AuthGroup;
import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.Entity.Post;
import com.sunnah.BookStore.data.Entity.User;
import com.sunnah.BookStore.data.repository.AuthGroupRepository;
import com.sunnah.BookStore.data.repository.BookRepository;
import com.sunnah.BookStore.data.repository.PostRepository;
import com.sunnah.BookStore.data.repository.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class BookStoreApplication implements CommandLineRunner {


	BookRepository bookRepository;
	PostRepository postRepository;
	UserRepository userRepository;
	UserService userService;
	AuthGroupRepository authGroupRepository;


	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book("Book for new life",
				"talk about how to start the new life",
				"/images/books/ImageBook1.png", "/pdfs/Book1.pdf", Language.DE, Categorie.CAT1);
		bookRepository.save(book);

		Post post = new Post("Why is Islam the best thing?",
				"Because islam is wonderful",
				"A blog (a truncation of weblog)[1] is a discussion or informational website published on the World Wide Web consisting of discrete, often informal diary-style text entries (posts). Posts are typically displayed in reverse chronoloer systems helps integrate MABs and single-author blogs into the news media. Blog can also be used as a verb, meaning to maintain or add content to a blog.",
				"Abdelrahman Elayashy");
		postRepository.save(post);

		/*List<AuthGroup> group = new ArrayList<>();
		AuthGroup authGroup = new AuthGroup("ADMIN");
		group.add(authGroup);

		User user = new User("alaiashy@outlook.com","asd",",asd",
				group, false);
		userService.signUpUser(user);
		authGroup.setUser(user);
		authGroupRepository.save(authGroup);*/
	}
}

