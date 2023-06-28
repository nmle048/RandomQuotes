package com.minhle.RandomQuotes.service.quote;

import com.minhle.RandomQuotes.model.AuthorModel;
import com.minhle.RandomQuotes.model.QuoteModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface QuoteService {
    ResponseEntity<?> createQuote(@RequestBody QuoteModel quoteModel);
    ResponseEntity<CollectionModel<QuoteModel>> getAllQuotes();
    ResponseEntity<QuoteModel> getQuoteById(@PathVariable int id);
    ResponseEntity<QuoteModel> updateQuote(@PathVariable int id, @RequestBody QuoteModel quoteModel);
    void deleteQuote(@PathVariable int id);

    ResponseEntity<QuoteModel> getRandomQuote();

}
