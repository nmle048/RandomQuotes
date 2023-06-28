package com.minhle.RandomQuotes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonRootName(value = "quote")
@Relation(collectionRelation = "quotes")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuoteModel extends RepresentationModel<QuoteModel> {
    private int id;
    private String quote;
    private AuthorModel authorModel;
}
