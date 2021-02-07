package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.data.Entity.AuthGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthGroupRepository extends CrudRepository<AuthGroup, Long> {
    List<AuthGroup> findByUserEmail(String email);
}
