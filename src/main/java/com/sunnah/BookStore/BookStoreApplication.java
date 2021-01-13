package com.sunnah.BookStore;

import com.sunnah.BookStore.business.domain.Categorie;
import com.sunnah.BookStore.business.service.UserService;
import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.Entity.Post;
import com.sunnah.BookStore.data.Entity.Role;
import com.sunnah.BookStore.data.Entity.User;
import com.sunnah.BookStore.data.repository.BookRepository;
import com.sunnah.BookStore.data.repository.PostRepository;
import com.sunnah.BookStore.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class BookStoreApplication implements CommandLineRunner {


	BookRepository bookRepository;
	PostRepository postRepository;
	UserRepository userRepository;
	UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = new Book("ser 3zeemmmmm mmmmmm mmmmmmmmmmmmm",
				"talk about ser 3zeemmmmmmmmmmm",
				"/images/books/ImageBook1.png", "/pdfs/Book1.pdf", Categorie.CAT1);
		bookRepository.save(book);

		Post post = new Post("Why is Islam the best thing?",
				"Because islam is wonderful",
				"A blog (a truncation of weblog)[1] is a discussion or informational website published on the World Wide Web consisting of discrete, often informal diary-style text entries (posts). Posts are typically displayed in reverse chronoloer systems helps integrate MABs and single-author blogs into the news media. Blog can also be used as a verb, meaning to maintain or add content to a blog.",
				"Abdelrahman Elayashy");
		postRepository.save(post);
		//Optional<User> user = userRepository.findByEmail("omar@outlook.com");
		//System.out.println("HI I am OMAR: " + user.get().getUsername());
		User user = new User("alaiashy@outlook.com","asd",",asd",
				Role.ADMIN, false);
	//	userRepository.deleteAll();
	//	userRepository.save(user);
		//userService.signUpUser(user);

	}
}

/*
<object data='pdfs/Book1.pdf#toolbar=1'
		type='application/pdf'
		width='100%'
		height='700px'>
</object>*/
