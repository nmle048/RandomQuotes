package com.minhle.RandomQuotes.service.author;

import com.minhle.RandomQuotes.model.AuthorModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthorService {

    ResponseEntity<?> createAuthor(@RequestBody AuthorModel authorModel);
    ResponseEntity<CollectionModel<AuthorModel>> getAllAuthors();
    ResponseEntity<AuthorModel> getAuthorById(@PathVariable int id);
    ResponseEntity<AuthorModel> updateAuthor(@PathVariable int id, @RequestBody AuthorModel authorModel);
    void deleteAuthor(@PathVariable int id);
}
