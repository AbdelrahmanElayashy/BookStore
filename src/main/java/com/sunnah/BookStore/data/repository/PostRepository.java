package com.sunnah.BookStore.data.repository;

import com.sunnah.BookStore.data.Entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

   // Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);

}