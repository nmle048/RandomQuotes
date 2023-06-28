package com.minhle.RandomQuotes.assembler;

import com.minhle.RandomQuotes.controller.AuthorController;
import com.minhle.RandomQuotes.controller.QuoteController;
import com.minhle.RandomQuotes.domain.Author;
import com.minhle.RandomQuotes.domain.Quote;
import com.minhle.RandomQuotes.model.AuthorModel;
import com.minhle.RandomQuotes.model.QuoteModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorModelAssembler extends RepresentationModelAssemblerSupport<Author, AuthorModel> {

    public AuthorModelAssembler() {
        super(AuthorController.class, AuthorModel.class);
    }

    @Override
    public AuthorModel toModel(Author entity) {
        AuthorModel authorModel = instantiateModel(entity);

        authorModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class)
                .getAuthorById(entity.getId()))
                .withSelfRel());

        authorModel.setId(entity.getId());
        authorModel.setName(entity.getName());
        authorModel.setQuoteModelList(toQuoteModel(entity.getQuotes()));

        return  authorModel;
    }

    @Override
    public CollectionModel<AuthorModel> toCollectionModel(Iterable<? extends Author> entities) {
        CollectionModel<AuthorModel> authorModels = super.toCollectionModel(entities);

        authorModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class)
                .getAllAuthors())
                .withSelfRel());

        return authorModels;
    }

    private List<QuoteModel> toQuoteModel(List<Quote> quotes) {
        if (quotes == null) {
            return Collections.EMPTY_LIST;
        }

        return quotes.stream()
                .map(quote -> QuoteModel.builder()
                        .id(quote.getId())
                        .quote(quote.getQuote())
                        .build()
                        .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuoteController.class)
                                .getQuoteById(quote.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
