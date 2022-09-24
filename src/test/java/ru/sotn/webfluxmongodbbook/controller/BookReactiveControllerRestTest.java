package ru.sotn.webfluxmongodbbook.controller;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.controller.hadler.BookHandler;
import ru.sotn.webfluxmongodbbook.domain.Author;
import ru.sotn.webfluxmongodbbook.domain.Book;
import ru.sotn.webfluxmongodbbook.domain.Genre;
import ru.sotn.webfluxmongodbbook.dto.BookDto;
import ru.sotn.webfluxmongodbbook.dto.mapper.BookMapper;
import ru.sotn.webfluxmongodbbook.repository.book.BookRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookReactiveControllerRest.class, BookHandler.class})
@WebFluxTest
class BookReactiveControllerRestTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private BookMapper bookMapper;
    @Autowired
    private WebTestClient webTestClient;

    private static final String NAME_BOOK = "Eva";
    private static final String ID_BOOK = "1";
    private static final String DATE_BOOK = "2000-01-02";
    private static final String NAME_GENRE = "Drama";
    private static final String AUTHOR_NAME = "Lermontov";
    private static final String AUTHOR_YEAR = "1989-05-06";
    private static final String ID_BOOK2 = "2";
    private static final String NAME_BOOK2 = "Anime";
    private static final String DATE_BOOK2 = "1999-10-22";
    private static final String NAME_GENRE2 = "Romans";
    private static final String AUTHOR_NAME2 = "Pushkin";
    private static final String AUTHOR_YEAR2 = "1985-05-21";


    @Test
    void shouldFindByAll(){
        val book1 = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        val book2 = new Book(ID_BOOK2, NAME_BOOK2, DATE_BOOK2, new Genre(NAME_GENRE2), new Author(AUTHOR_NAME2, AUTHOR_YEAR2));


        Flux<Book> bookFlux = Flux.just(book1, book2);
        when(bookRepository.findAll()).thenReturn(bookFlux);

        when(bookMapper.bookConvertToDto(any())).thenReturn(new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, NAME_GENRE)).thenReturn(new BookDto(ID_BOOK2, NAME_BOOK2, DATE_BOOK2
                , AUTHOR_NAME2, AUTHOR_YEAR2,NAME_GENRE2));

        webTestClient.get()
                .uri("/react/book")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .value(books -> {
                    assertThat(books.get(0).getIdBook()).isEqualTo(ID_BOOK);
                    assertThat(books.get(1).getIdBook()).isEqualTo(ID_BOOK2);
                });
    }
    @Test
    void shouldFindByID(){
        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        Mono<Book> bookMono = Mono.just(book);
        when(bookRepository.findById(book.getId())).thenReturn(bookMono);
        when(bookMapper.bookConvertToDto(any())).thenReturn(new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, NAME_GENRE));

        webTestClient.get()
                .uri("/react/book/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(bookDto -> assertThat(bookDto.getIdBook()).isEqualTo(ID_BOOK));

    }
    @Test
    void shouldCreateBook(){

        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        val bookDto = new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, NAME_GENRE);

        Mono<Book> bookMono = Mono.just(book);
        when(bookRepository.insert(book)).thenReturn(bookMono);

        when(bookMapper.bookConvertToDto(any())).thenReturn(bookDto);

        when(bookMapper.fromBookDtoToBook(any())).thenReturn(book);


        webTestClient.post()
                .uri("/react/book")
                .body(Mono.just(bookDto), BookDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDto.class)
                .value(bookDto1 -> assertThat(bookDto1.getIdBook()).isEqualTo(ID_BOOK));
    }

    @Test
    void shouldDeleteBookById(){
        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        Mono<Book> bookMono = Mono.just(book);
        Mono<Void> deleteMono = bookMono.then();
        when(bookRepository.findById(ID_BOOK)).thenReturn(bookMono);
        when(bookRepository.delete(book)).thenReturn(deleteMono);

        webTestClient.delete()
                .uri("/react/book/delete/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(bookDto -> assertThat(bookDto).isNull());

    }
}