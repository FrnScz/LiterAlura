package br.com.alura.challenge.LiterAlura.controllers;


import br.com.alura.challenge.LiterAlura.models.entities.AuthorEntity;
import br.com.alura.challenge.LiterAlura.models.entities.BookEntity;
import br.com.alura.challenge.LiterAlura.models.records.Books;
import br.com.alura.challenge.LiterAlura.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PrincipalController {
    private final BooksService booksService;
    Scanner scanner = new Scanner(System.in);

    @Autowired
    public PrincipalController(BooksService booksService) {
        this.booksService = booksService;
    }

    public void showMenu() {
        System.out.println("********************************");
        System.out.println("Bem vindo ao sistema de consulta de livros!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Buscar livros pelo título ou autor");
        System.out.println("2 - Buscar livros pelo Id");
        System.out.println("3 - Listar livros salvos");
        System.out.println("4 - Listar autores salvos");
        System.out.println("5 - Listar autores vivos em determinado ano");
        System.out.println("6 - Listar livros em determinado idioma");
        System.out.println("0 - Sair");
        System.out.println("********************************");
        String opt = scanner.nextLine();

        switch (opt) {
            case "1":
                getAuthorNameOrTitle();
                break;
            case "2":
                getDataByBookId();
                break;
            case "3":
                getAllBooksFromDb();
                break;
            case "4":
                getAllAuthorsFromDb();
                break;
            case "5":
                String year = getYearInfo();
                getAllAuthorsBetweenYears(year);
                break;
            case "6":
                String language = getLanguageInfo();
                getBooksByLanguage(language);
                break;
            case "0":
                System.out.println("Saindo do sistema");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                showMenu();
                break;
        }
    }

    public void getAuthorNameOrTitle() {
        System.out.println("Digite o nome do autor ou título do livro:");
        String nameOrTitle = scanner.nextLine();
        Books books = booksService.getAllMediaDataByName(nameOrTitle);
        if(books.count() == 0) {
            System.out.println("Nenhum livro encontrado");
            showMenu();
        } else {
            showBooks(books, nameOrTitle);
            getDataByBookId();
        }
    }

    public void getDataByBookId() {
        System.out.println("Digite o ID do livro:");
        String id = scanner.nextLine();
        BookEntity book = booksService.getMediaDataById(id);
        System.out.println(book);
        showMenu();
    }

    public void showBooks(Books books, String nameOrTitle) {
        System.out.println("** Foram encontrados " + books.count() + " livros" +
                " " +
                "como resultado da busca por '" + nameOrTitle + "':" + "\n");
        books.books().forEach(System.out::println);
    }

    public void getAllBooksFromDb() {
        System.out.println("********************************");
        booksService.getAllBooksFromDb().forEach(System.out::println);
        showMenu();
    }

    public void getAllAuthorsFromDb() {
        System.out.println("** Autores salvos no banco de dados ** \n");
        Map<String, AuthorEntity> uniqueAuthorsMap =
                booksService.getAllAuthorsFromDb().stream()
                        .collect(Collectors.toMap(
                                AuthorEntity::getName,
                                Function.identity(),
                                (existing, replacement) -> existing
                        ));
        Collection<AuthorEntity> uniqueAuthors = uniqueAuthorsMap.values();
        uniqueAuthors.forEach(System.out::println);
        showMenu();
    }

    private String getYearInfo() {
        System.out.println("Digite o ano para pesquisa:");
        return scanner.nextLine();
    }

    private void getAllAuthorsBetweenYears(String yearString) {
        System.out.println("** Autores presentes em banco de dados que " +
                "estavam vivos em " + yearString + " ** \n");
        int year = Integer.parseInt(yearString);
        List<AuthorEntity> authors = booksService.getAllAuthorsFromDb();
        Map<String, AuthorEntity> uniqueAuthorsMap = authors.stream()
                .filter(author -> (author.getBirthYear() <= year &&
                        author.getBirthYear() != 0) &&
                        (author.getDeathYear() >= year &&
                                author.getDeathYear() != 0))
                .collect(Collectors.toMap(
                        AuthorEntity::getName,
                        Function.identity(),
                        (existing, replacement) -> existing
                ));
        Collection<AuthorEntity> uniqueAuthors = uniqueAuthorsMap.values();
        uniqueAuthors.forEach(System.out::println);
        showMenu();
    }

    private String getLanguageInfo() {
        System.out.println("** Digite o idioma para pesquisa: (apenas 2 " +
                "caracteres) **");
        return scanner.nextLine();
    }

    private void getBooksByLanguage(String language) {
        List<BookEntity> books = booksService.getAllBooksFromDb();
        Set<BookEntity> uniqueBooks = books.stream()
                .filter(book -> book.getLanguages().contains(language))
                .collect(Collectors.toSet());
        if(uniqueBooks.isEmpty()) {
            System.out.println("Nenhum livro encontrado em " + language);

        } else {
            System.out.println("** Livros encontrados em " + language + " **");
            uniqueBooks.forEach(System.out::println);
        }
        showMenu();
    }
}