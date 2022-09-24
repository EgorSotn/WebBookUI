package ru.sotn.webfluxmongodbbook.repository.author;


import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Author;

import java.util.Optional;

public interface AuthorRepositoryCustom {
    Mono<Author> getByNameOrCreate(Author author);
}
