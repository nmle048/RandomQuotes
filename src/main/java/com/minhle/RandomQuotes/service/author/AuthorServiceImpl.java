package com.minhle.RandomQuotes.service.author;

import com.minhle.RandomQuotes.assembler.AuthorModelAssembler;
import com.minhle.RandomQuotes.domain.Author;
import com.minhle.RandomQuotes.model.AuthorModel;
import com.minhle.RandomQuotes.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorModelAssembler authorModelAssembler;

    @Override
    public ResponseEntity<?> createAuthor(AuthorModel authorModel) {
        if (authorRepository.existsByName(authorModel.getName())) {
            return new ResponseEntity<>("Author already exists", HttpStatus.CONFLICT);
        }

        Author author = new Author();
        author.setName(authorModel.getName());

        authorRepository.save(author);

        return new ResponseEntity<>(
                authorModelAssembler.toModel(author),
                HttpStatus.CREATED
        );
    }

    @Override
    public ResponseEntity<CollectionModel<AuthorModel>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return new ResponseEntity<>(
                authorModelAssembler.toCollectionModel(authors),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<AuthorModel> getAuthorById(int id) {
        return authorRepository.findById(id)
                .map(authorModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<AuthorModel> updateAuthor(int id, AuthorModel authorModel) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        author.setName(authorModel.getName());
        authorRepository.save(author);

        return new ResponseEntity<>(
                authorModelAssembler.toModel(author),
                HttpStatus.OK
        );
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
