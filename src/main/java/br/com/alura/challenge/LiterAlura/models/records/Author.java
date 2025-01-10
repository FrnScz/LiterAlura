package br.com.alura.challenge.LiterAlura.models.records;

import br.com.alura.challenge.LiterAlura.models.entities.AuthorEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Author(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") int birthYear,
        @JsonProperty("death_year") int deathYear
) {
    public AuthorEntity toEntity() {
        return new AuthorEntity(this);
    }

    @Override
    public String toString() {
        return "Autor: " + name +
                ", Ano de Nascimento: " + (birthYear == 0 ? "Data não " +
                "informada" : birthYear) +
                ", Ano de Morte: " + (deathYear == 0 ?
                "Data não informada" : deathYear);
    }
}
