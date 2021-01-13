package com.sunnah.BookStore.data.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "BASKET")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy="basket")
    private User user;

    @OneToMany(mappedBy="basket")
    private List<BasketItem> items = new ArrayList<>();

    public void removeItem(BasketItem item) {
        if(this.items != null) {
            this.items.remove(item);
        }
    }

    public void addItem(BasketItem item) {
        if(this.items == null) {
            this.items = new ArrayList<>();
        }
        item.setBasket(this);
        this.items.add(item);
    }


}
