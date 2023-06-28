package com.minhle.RandomQuotes.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@JsonRootName(value = "author")
@Relation(collectionRelation = "authors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorModel extends RepresentationModel<AuthorModel> {
    private int id;
    private String name;
    private List<QuoteModel> quoteModelList;
}
