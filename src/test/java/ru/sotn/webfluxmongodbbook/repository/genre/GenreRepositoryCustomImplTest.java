package ru.sotn.webfluxmongodbbook.repository.genre;

import lombok.val;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;
import ru.sotn.webfluxmongodbbook.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class GenreRepositoryCustomImplTest {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("должен добавлять в бд жанра или возвращать по его ")
    @Test
    void getByNameOrCreate() {
        val genre = mongoTemplate.save(new Genre("prosa")).block();
        val firstGenre = genreRepository.findAll().take(1);
        val actualGenre  = genreRepository.getByNameOrCreate(genre);

        StepVerifier.create(firstGenre).assertNext(g-> {
           actualGenre.map(actualG-> assertThat(actualG).isEqualTo(g));
        }).expectComplete().verify();




        val newGenre = new Genre("aaaa" );
        val actualCommentElse = genreRepository.getByNameOrCreate(newGenre);
        StepVerifier.create(actualCommentElse).assertNext(a->
                assertThat(a).isEqualTo(newGenre)).expectComplete().verify();



    }
}