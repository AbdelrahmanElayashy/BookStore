package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.business.domain.Language;
import com.sunnah.BookStore.data.Entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByNameLike(String name);

    List<Book> findAllByLanguage(Language language);


}
