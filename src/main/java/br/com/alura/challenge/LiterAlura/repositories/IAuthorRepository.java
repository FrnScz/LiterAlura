package br.com.alura.challenge.LiterAlura.repositories;

import br.com.alura.challenge.LiterAlura.models.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthorRepository extends JpaRepository<AuthorEntity, Long> {
    Optional<AuthorEntity> findFirstByName(String name);
}