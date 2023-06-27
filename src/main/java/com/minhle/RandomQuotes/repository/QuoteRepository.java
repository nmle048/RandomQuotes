package com.minhle.RandomQuotes.repository;

import com.minhle.RandomQuotes.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
}
