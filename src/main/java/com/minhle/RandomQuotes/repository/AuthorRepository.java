package com.minhle.RandomQuotes.repository;

import com.minhle.RandomQuotes.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByName(String name);
}
