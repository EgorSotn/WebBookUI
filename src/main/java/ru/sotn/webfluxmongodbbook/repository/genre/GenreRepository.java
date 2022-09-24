package ru.sotn.webfluxmongodbbook.repository.genre;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.sotn.webfluxmongodbbook.domain.Genre;


public interface GenreRepository extends ReactiveMongoRepository<Genre, String>, GenreRepositoryCustom {

}