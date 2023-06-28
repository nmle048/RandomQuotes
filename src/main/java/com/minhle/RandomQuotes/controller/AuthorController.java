package com.minhle.RandomQuotes.controller;

import com.minhle.RandomQuotes.model.AuthorModel;
import com.minhle.RandomQuotes.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost/8080")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorModel authorModel) {
        return authorService.createAuthor(authorModel);
    }

    @GetMapping("/authors")
    public ResponseEntity<CollectionModel<AuthorModel>> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorModel> updateAuthor(@PathVariable int id, @RequestBody AuthorModel authorModel) {
        return authorService.updateAuthor(id, authorModel);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
