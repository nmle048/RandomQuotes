package com.minhle.RandomQuotes.controller;

import com.minhle.RandomQuotes.assembler.AuthorModelAssembler;
import com.minhle.RandomQuotes.assembler.QuoteModelAssembler;
import com.minhle.RandomQuotes.domain.Author;
import com.minhle.RandomQuotes.domain.Quote;
import com.minhle.RandomQuotes.model.QuoteModel;
import com.minhle.RandomQuotes.repository.AuthorRepository;
import com.minhle.RandomQuotes.repository.QuoteRepository;
import com.minhle.RandomQuotes.service.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @PostMapping("/quotes")
    public ResponseEntity<?> createQuote(@RequestBody QuoteModel quoteModel) {
        return quoteService.createQuote(quoteModel);
    }

    @GetMapping("/quotes")
    public ResponseEntity<CollectionModel<QuoteModel>> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/quotes/{id}")
    public ResponseEntity<QuoteModel> getQuoteById(@PathVariable int id) {
        return quoteService.getQuoteById(id);
    }

    @PutMapping("/quotes/{id}")
    public ResponseEntity<QuoteModel> updateQuote(@PathVariable int id, @RequestBody QuoteModel quoteModel) {
        return quoteService.updateQuote(id, quoteModel);
    }

    @DeleteMapping("/quotes/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable int id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/quotes/random")
    public ResponseEntity<QuoteModel> getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
