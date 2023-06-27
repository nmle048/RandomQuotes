package com.minhle.RandomQuotes.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(
            mappedBy = "authors",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Quote> quotes;
}
