package com.sunnah.BookStore.business.domain;

import com.sunnah.BookStore.data.Entity.Basket;
import com.sunnah.BookStore.data.Entity.BasketItem;
import com.sunnah.BookStore.data.Entity.Book;
import lombok.Data;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@SessionScope
public class BasketHolder {


    private Basket basket;

    public BasketHolder() {
        this.reset();
    }

    public void add(Book book, int amount) {
        if (basket == null) {
            initBasket();
        }
        if (!this.findBook(book.getId())) {
            basket.addItem(toBasketItem(book, 1));
        }
    }

    public void addAmount(long bookId, int amount) {
        basket.getItems().forEach(item -> {
            if (item.getBook().getId() == bookId) {
                item.setAmount(amount);
            }
        });
    }

    public void delete(long bookId) {
        if (basket == null)
            return;
        List<BasketItem> items = new ArrayList<>();
        for (BasketItem item : basket.getItems()) {
            if (item.getBook().getId() == bookId) {
                items.add(item);
            }
        }
        basket.getItems().removeAll(items);
    }

    private BasketItem toBasketItem(Book book, int amount) {
        BasketItem item = new BasketItem();
        item.setBook(book);
        item.setAmount(amount);
        return item;
    }


    private boolean findBook(long bookId) {
        for(BasketItem item : basket.getItems()) {
            if (item.getBook().getId() == bookId)
                return true;
        }
        return false;
    }

    public void reset() {
        initBasket();
    }

    private void initBasket() {
        basket = new Basket();

    }
}