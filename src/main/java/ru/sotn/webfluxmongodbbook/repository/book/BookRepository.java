package ru.sotn.webfluxmongodbbook.repository.book;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.sotn.webfluxmongodbbook.domain.Book;

import java.util.List;


public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {
    @Override
    Flux<Book> findAll();
    //    @EntityGraph(attributePaths = {"author","genres"})
//    List<Book> findAll();
//
//    @Modifying
//    @Query("update Book b set b.name =:name WHERE b.idBook =:id")
//    void updateNameById(@Param("name")String name,@Param("id") long id);
}