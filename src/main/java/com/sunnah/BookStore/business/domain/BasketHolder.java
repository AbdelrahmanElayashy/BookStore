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

    public void add(long bookId, int amount) {
        if(basket == null) {
            initBasket();
        }
        if(!this.findBook(bookId)) {
            basket.addItem(toBasketItem(bookId, amount));
        }
    }

    public void delete(long bookId) {
        if(basket == null)
            return;
        List<BasketItem> items = new ArrayList<>();
        for(BasketItem item : basket.getItems()) {
            if(item.getBookId() == bookId) {
                items.add(item);
            }
        }
        basket.getItems().removeAll(items);
    }

    private BasketItem toBasketItem(long bookId, int amount) {
        BasketItem item = new BasketItem();
        item.setBookId(bookId);
        item.setAmount(amount);
        return item;
    }


    private boolean findBook(long bookId) {

        for(BasketItem item : basket.getItems()) {
            if(item.getBookId() == bookId)
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