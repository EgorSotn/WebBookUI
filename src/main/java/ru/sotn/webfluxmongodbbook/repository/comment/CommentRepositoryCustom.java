package ru.sotn.webfluxmongodbbook.repository.comment;


import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Comment;

import java.util.Optional;

public interface CommentRepositoryCustom {
    Mono<Comment> getByNameOrCreate(Comment comment);
}
