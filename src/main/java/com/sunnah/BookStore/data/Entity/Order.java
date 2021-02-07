package com.sunnah.BookStore.data.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "`ORDER`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private Basket basket;

    private boolean closed;

    private long requestNumber;
}
