package ru.sotn.webfluxmongodbbook.repository.author;


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
import ru.sotn.webfluxmongodbbook.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class AuthorRepositoryCustomImplMongoTest {


//    @Autowired
//    private TestEntityManager em;
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;



    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("должен добавлять в бд автора или возвращать по его ")
    @Test
    void getByNameOrCreate() {

        val author = mongoTemplate.save(new Author("Pushkin", "1812-01-01")).block();
        val firstAuthor = authorRepository.findAll().take(1);
        val actualAuthor  = authorRepository.getByNameOrCreate(author);
        StepVerifier
                .create(firstAuthor).assertNext(a-> {
                    actualAuthor.map(actualA-> assertThat(actualA).isEqualTo(a));
                }).expectComplete().verify();


        val newAuthor = new Author("aaaa", "1993-05-11");
        val actualAuthorElse = authorRepository.getByNameOrCreate(newAuthor);
        StepVerifier.create(actualAuthorElse).assertNext(a->
                assertThat(a).isEqualTo(newAuthor)).expectComplete().verify();






    }
}