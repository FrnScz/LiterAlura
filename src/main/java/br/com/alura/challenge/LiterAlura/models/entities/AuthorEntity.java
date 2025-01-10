package br.com.alura.challenge.LiterAlura.models.entities;

import br.com.alura.challenge.LiterAlura.models.records.Author;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "death_year")
    private int deathYear;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch =
            FetchType.EAGER)
    private List<BookEntity> books = new ArrayList<>();

    /**
     * CONSTRUCTORS
     */
    public AuthorEntity() {
    }

    public AuthorEntity(Author author) {
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    /**
     * GETTERS AND SETTERS
     */
    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Autor: " + name +
                ", Ano de Nascimento: " + (birthYear == 0 ? "Data não " +
                "informada" : birthYear) +
                ", Ano de Morte: " + (deathYear == 0 ?
                "Data não informada." : deathYear + ".");
    }
}