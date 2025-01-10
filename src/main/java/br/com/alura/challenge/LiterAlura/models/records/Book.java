package br.com.alura.challenge.LiterAlura.models.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Book(
        @JsonProperty("id") int id,
        @JsonProperty("title") String title,
        @JsonProperty("authors") List<Author> authors,
        @JsonProperty("languages") List<String> languages,
        @JsonAlias("download_count") int downloads
) {
    @Override
    public String toString() {
        return "id:" + id + '\n' +
                "Título: " + title + '\n' +
                authors + '\n' +
                "Idiomas: " + languages + ", Quantidade de downloads: " + downloads + '\n';
    }
}