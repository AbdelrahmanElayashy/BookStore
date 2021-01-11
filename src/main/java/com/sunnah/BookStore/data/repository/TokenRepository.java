package com.sunnah.BookStore.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sunnah.BookStore.data.Entity.Token;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long> {
}
