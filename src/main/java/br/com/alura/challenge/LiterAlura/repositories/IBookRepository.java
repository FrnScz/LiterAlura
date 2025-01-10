package br.com.alura.challenge.LiterAlura.repositories;

import br.com.alura.challenge.LiterAlura.models.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, Long> {
}
