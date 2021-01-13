package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.data.Entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
}
