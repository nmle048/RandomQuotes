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

@Component
public class QuoteModelAssembler extends RepresentationModelAssemblerSupport<Quote, QuoteModel> {

    public QuoteModelAssembler() {
        super(QuoteController.class, QuoteModel.class);
    }

    @Override
    public QuoteModel toModel(Quote entity) {
        QuoteModel quoteModel = instantiateModel(entity);

        quoteModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuoteController.class)
                .getQuoteById(entity.getId()))
                .withSelfRel());

        quoteModel.setId(entity.getId());
        quoteModel.setQuote(entity.getQuote());
        quoteModel.setAuthorModel(toAuthorModel(entity.getAuthor()));

        return quoteModel;
    }

    @Override
    public CollectionModel<QuoteModel> toCollectionModel(Iterable<? extends Quote> entities) {
        CollectionModel<QuoteModel> quoteModels = super.toCollectionModel(entities);

        quoteModels.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(QuoteController.class)
                .getAllQuotes())
                .withSelfRel());

        return quoteModels;
    }

    private AuthorModel toAuthorModel(Author author) {
        return AuthorModel.builder()
                .id(author.getId())
                .name(author.getName())
                .build()
                .add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class)
                        .getAuthorById(author.getId()))
                        .withSelfRel());
    }
}
