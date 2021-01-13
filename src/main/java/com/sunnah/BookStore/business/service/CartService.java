package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.business.domain.BasketHolder;
import com.sunnah.BookStore.data.Entity.BasketItem;
import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@Service
public class CartService {

    @Autowired private BasketHolder basketHolder;
    @Autowired private BookRepository bookRepository;

    public boolean addItemToCart(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()) {
            return false;
        }
        basketHolder.add(bookId, 1);
        return true;
    }

    public List<BasketItem> listBasket() {
        return basketHolder.getBasket().getItems();
    }

    public List<Book> listBookInCart() {

        List<Book> books = listBasket().stream().map(item -> {
            return bookRepository.findById(item.getBookId()).get();
        }).collect(toList());

        return books;
    }

    public void deleteItemFromCart(long bookId) {
        this.basketHolder.delete(bookId);
    }
}
