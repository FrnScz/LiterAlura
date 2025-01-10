package br.com.alura.challenge.LiterAlura.models.entities;

import br.com.alura.challenge.LiterAlura.models.records.Author;
import br.com.alura.challenge.LiterAlura.models.records.Book;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> authors = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages = new ArrayList<>();
    private int downloads;

    /**
     * CONSTRUCTORS
     */
    public BookEntity() {
    }

    public BookEntity(final Book book) {
        this.id = (long) book.id();
        this.title = book.title();

        for (Author author : book.authors()) {
            this.authors.add(new AuthorEntity(author));
        }

        this.languages = book.languages();
        this.downloads = book.downloads();
    }

    /**
     * GETTERS AND SETTERS
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    /**
     * METHODS
     */
    @Override
    public String toString() {
        return "-----LIVRO-----" + '\n' +
                "Titulo: " + title + '\n' +
                authors + '\n' +
                "Idioma: " + languages + '\n' +
                "Número de downloads: " + downloads + '\n' +
                "-----------------";
    }
}
