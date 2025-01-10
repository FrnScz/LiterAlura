package br.com.alura.challenge.LiterAlura.services;

import br.com.alura.challenge.LiterAlura.models.entities.BookEntity;
import br.com.alura.challenge.LiterAlura.models.records.Book;
import br.com.alura.challenge.LiterAlura.models.records.Books;
import br.com.alura.challenge.LiterAlura.services.interfaces.IConvertData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertDataService implements IConvertData {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T fromJson(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookEntity> toBookEntities(Books books) {
        return books.books().stream()
                .map(this::toBookEntity)
                .collect(Collectors.toList());
    }

    private BookEntity toBookEntity(Book book) {
        return new BookEntity(book);
    }
}