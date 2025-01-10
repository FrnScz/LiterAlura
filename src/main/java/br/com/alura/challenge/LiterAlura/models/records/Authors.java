package br.com.alura.challenge.LiterAlura.models.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Authors (
        @JsonProperty("Authors") List<Author> authors
){
    @Override
    public String toString() {
        return "Authors: " + authors;
    }
}
