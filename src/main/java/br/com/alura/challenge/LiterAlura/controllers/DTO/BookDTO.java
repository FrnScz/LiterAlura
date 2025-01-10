package br.com.alura.challenge.LiterAlura.controllers.DTO;

import br.com.alura.challenge.LiterAlura.models.records.Author;

import java.util.List;

public record BookDTO (int id,
                       String title,
                       List<Author> authors,
                       List<String> languages,
                       int downloads) {
}
