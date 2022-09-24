package ru.sotn.webfluxmongodbbook.repository.author;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.sotn.webfluxmongodbbook.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author,String>, AuthorRepositoryCustom {
}