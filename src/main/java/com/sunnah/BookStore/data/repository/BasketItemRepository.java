package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.data.Entity.BasketItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {

}
