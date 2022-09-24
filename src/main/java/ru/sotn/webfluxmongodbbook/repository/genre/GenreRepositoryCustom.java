package ru.sotn.webfluxmongodbbook.repository.genre;


import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Genre;

import java.util.Optional;

public interface GenreRepositoryCustom {
    Mono<Genre> getByNameOrCreate(Genre genre);
}
