package ru.sotn.webfluxmongodbbook.repository.comment;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.sotn.webfluxmongodbbook.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String>,CommentRepositoryCustom {
//    @EntityGraph(attributePaths = {"book"})
//    @Override
//    List<Comment> findAll();
}
