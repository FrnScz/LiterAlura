package br.com.alura.challenge.LiterAlura.models.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Books(
        @JsonProperty("count") int count,
        @JsonProperty("results") List<Book> books
) {}