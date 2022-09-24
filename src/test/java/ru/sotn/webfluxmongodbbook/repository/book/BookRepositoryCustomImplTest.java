package ru.sotn.webfluxmongodbbook.repository.book;

import lombok.val;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sotn.webfluxmongodbbook.domain.Author;
import ru.sotn.webfluxmongodbbook.domain.Book;
import ru.sotn.webfluxmongodbbook.domain.Genre;

import java.util.Collections;

import static com.mongodb.assertions.Assertions.assertFalse;
import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@ExtendWith(SpringExtension.class)
class BookRepositoryCustomImplTest  {
    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    @Autowired
    BookRepository bookRepository;

    @Test
    void shouldExistBookToReturnBool(){
//        val genre = Collections.singletonList( new Genre("prosa"));
//        val author = new Author("Lermonov", "1812-01-01");
//        val book = new Book("War and Peace", "1834-01-01", genre, author, null);
//        val isNotExist = bookRepository.isExistBook(book);
//        assertFalse(isNotExist);
//        val saveBook = mongoTemplate.save(book);
//
//        val isExist = bookRepository.isExistBook(saveBook);
//        assertTrue(isExist);
    }
}