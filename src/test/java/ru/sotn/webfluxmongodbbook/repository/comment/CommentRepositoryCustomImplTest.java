package ru.sotn.webfluxmongodbbook.repository.comment;

import lombok.val;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import ru.sotn.webfluxmongodbbook.domain.Comment;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class CommentRepositoryCustomImplTest  {


    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("должен добавлять в бд комментарии или возвращать по его")
    @Test
    void getByNameOrCreate() {
        val comment = mongoTemplate.save(new Comment("adsad")).block();
        val firstComment = commentRepository.findAll().take(1);
        val actualComment  = commentRepository.getByNameOrCreate(comment);

        StepVerifier.create(actualComment).assertNext(a->{
            firstComment.map(firstC->assertThat(a).isEqualTo(firstC));
        }).expectComplete().verify();



        val newComment = new Comment("asfdfgh" );
        val actualCommentElse = commentRepository.getByNameOrCreate(newComment);
        StepVerifier.create(actualCommentElse).assertNext(a->
                assertThat(a).isEqualTo(newComment)).expectComplete().verify();


    }
}