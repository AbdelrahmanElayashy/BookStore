package com.sunnah.BookStore.business.service;

import com.sunnah.BookStore.business.domain.BasketHolder;
import com.sunnah.BookStore.data.Entity.BasketItem;
import com.sunnah.BookStore.data.Entity.Book;
import com.sunnah.BookStore.data.repository.BasketItemRepository;
import com.sunnah.BookStore.data.repository.BasketRepository;
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
@AllArgsConstructor
@Service
public class CartService {

    @Autowired
    private final BasketHolder basketHolder;
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final BasketRepository basketRepository;
    @Autowired
    private final BasketItemRepository basketItemRepository;

    public boolean addItemToCart(long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return false;
        }
        basketHolder.add(book.get(), 1);
        return true;
    }

    public boolean addAmountToItem(long bookId, int amount) {
        basketHolder.addAmount(bookId, amount);
        return true;
    }

    public List<BasketItem> listBasket() {
        return basketHolder.getBasket().getItems();
    }

    public List<Book> listBookInCart() {

        List<Book> books = listBasket().stream().map(item -> {
            return bookRepository.findById(item.getBook().getId()).get();
        }).collect(toList());

        return books;
    }

    public void deleteItemFromCart(long bookId) {
        this.basketHolder.delete(bookId);
    }

    public void saveBasketItem(BasketItem basketItem) {
        this.basketItemRepository.save(basketItem);
    }
}
