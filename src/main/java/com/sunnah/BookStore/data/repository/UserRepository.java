package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.data.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByEmail(@Param("email") String email);
}
