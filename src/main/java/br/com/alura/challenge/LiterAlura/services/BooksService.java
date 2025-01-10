package br.com.alura.challenge.LiterAlura.services;

import br.com.alura.challenge.LiterAlura.integration.BooksApiIntegration;
import br.com.alura.challenge.LiterAlura.models.entities.AuthorEntity;
import br.com.alura.challenge.LiterAlura.models.entities.BookEntity;
import br.com.alura.challenge.LiterAlura.models.records.Books;
import br.com.alura.challenge.LiterAlura.repositories.IAuthorRepository;
import br.com.alura.challenge.LiterAlura.repositories.IBookRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BooksService {

    private final IAuthorRepository authorsRepository;
    private final IBookRepository booksRepository;
    private final BooksApiIntegration booksApiIntegration;
    private final ConvertDataService convertService;
    String ADDRESS = "https://gutendex.com/books/?";

    public BooksService(IAuthorRepository authorsRepository,
                        IBookRepository booksRepository,
                        BooksApiIntegration booksApiIntegration,
                        ConvertDataService convertService)
    {
        this.authorsRepository = authorsRepository;
        this.booksRepository = booksRepository;
        this.booksApiIntegration = booksApiIntegration;
        this.convertService = convertService;
    }

    public Books getAllMediaDataByName(String bookName) {
        try {
            String endpoint =
                    ADDRESS + "search=" +bookName.toLowerCase().replace(" ",
                            "%20");
            String json = booksApiIntegration.getApiBooksData(endpoint);
            return convertService.fromJson(json, Books.class);
        } catch (Exception e) {
            throw new RuntimeException("Media not found" + e.getMessage());
        }
    }

    public BookEntity getMediaDataById(String Id) {
        try {
            String endpoint =
                    ADDRESS + "ids=" + Id;
            String json = booksApiIntegration.getApiBooksData(endpoint);
            Books book = convertService.fromJson(json, Books.class);
            BookEntity bookEntity =
                    convertService.toBookEntities(book).getFirst();
            getBookById(Id).ifPresentOrElse(
                    b -> {},
                    () -> saveBook(bookEntity)
            );
            return bookEntity;
        } catch (Exception e) {
            throw new RuntimeException("Media not found" + e.getMessage());
        }
    }

    public List<BookEntity> getAllBooksFromDb() {
        try {
            return booksRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<AuthorEntity> getAllAuthorsFromDb() {
        try {
            return authorsRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public Optional<BookEntity> getBookById(String id) {
        return booksRepository.findById(Long.parseLong(id));
    }

    public void saveBook(BookEntity bookEntity) {
        booksRepository.save(bookEntity);
    }

    public void saveAuthor(AuthorEntity authorEntity) {
        authorsRepository.save(authorEntity);
    }

}
