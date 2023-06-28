package com.minhle.RandomQuotes.service.quote;

import com.minhle.RandomQuotes.assembler.QuoteModelAssembler;
import com.minhle.RandomQuotes.domain.Author;
import com.minhle.RandomQuotes.domain.Quote;
import com.minhle.RandomQuotes.model.QuoteModel;
import com.minhle.RandomQuotes.repository.AuthorRepository;
import com.minhle.RandomQuotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService{

    @Autowired
    QuoteRepository quoteRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    QuoteModelAssembler quoteModelAssembler;

    @Override
    public ResponseEntity<?> createQuote(QuoteModel quoteModel) {
        Quote quote = new Quote();
        quote.setQuote(quoteModel.getQuote());

        quoteRepository.save(quote);

        Author author = authorRepository.findById(quoteModel.getAuthorModel().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.addQuote(quote);
        authorRepository.save(author);

        quote.setAuthor(author);
        quoteRepository.save(quote);

        return new ResponseEntity<>(
                quoteModelAssembler.toModel(quote),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CollectionModel<QuoteModel>> getAllQuotes() {
        List<Quote> quotes = quoteRepository.findAll();

        return new ResponseEntity<>(
                quoteModelAssembler.toCollectionModel(quotes),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<QuoteModel> getQuoteById(int id) {
        return quoteRepository.findById(id)
                .map(quoteModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<QuoteModel> updateQuote(int id, QuoteModel quoteModel) {
        Quote quote = quoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quote not found"));
        Author author = authorRepository.findById(quoteModel.getAuthorModel().getId())
                        .orElseThrow(() -> new RuntimeException("Author not found"));
        quote.setQuote(quoteModel.getQuote());
        quote.setAuthor(author);

        quoteRepository.save(quote);

        return new ResponseEntity<>(
                quoteModelAssembler.toModel(quote),
                HttpStatus.OK
        );
    }

    @Override
    public void deleteQuote(int id) {
        quoteRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<QuoteModel> getRandomQuote() {

        return quoteRepository.findById(getRandomNumber())
                .map(quoteModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private int getRandomNumber() {
        long quoteCount = quoteRepository.count();
        int randomNumber =  (int) Math.floor(Math.random() * quoteCount);

        if (randomNumber == 0) return getRandomNumber();

        return randomNumber;
    }
}
