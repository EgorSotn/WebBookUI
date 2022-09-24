package ru.sotn.webfluxmongodbbook.controller.hadler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Book;
import ru.sotn.webfluxmongodbbook.dto.BookDto;
import ru.sotn.webfluxmongodbbook.dto.mapper.BookMapper;
import ru.sotn.webfluxmongodbbook.repository.book.BookRepository;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@AllArgsConstructor
public class BookHandler {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Mono<ServerResponse> getAllBook(ServerRequest serverRequest){
        return ok().contentType(MediaType.APPLICATION_JSON).body(bookRepository.findAll()
                .map( bookMapper::bookConvertToDto),BookDto.class);
    }
    public Mono<ServerResponse> getBookById(ServerRequest serverRequest){

        return bookRepository.findById(serverRequest.pathVariable("id")).map( bookMapper::bookConvertToDto)
                .flatMap(book -> ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(book), BookDto.class))
                .switchIfEmpty(notFound().build());
    }
    public Mono<ServerResponse> createBook(ServerRequest serverRequest){
        Mono<Book> bookMono = serverRequest.bodyToMono(BookDto.class).map(bookMapper::fromBookDtoToBook);
        return  bookMono.flatMap(book ->
                        ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                                .body(bookRepository.insert(book).map(bookMapper::bookConvertToDto),BookDto.class));
    }

    public Mono<ServerResponse> updateBook(ServerRequest serverRequest){
        Mono<BookDto> bookMono = serverRequest.bodyToMono(BookDto.class);

        Mono<Book> byId = bookRepository.findById(serverRequest.pathVariable("id"));

        return  bookMono.zipWith( byId, ((nb, old) -> (
                new BookDto(old.getId(), nb.getNameBook(),nb.getYearBook(),
                        nb.getNameAuthor(), nb.getYearAuthor(), nb.getNamesGenre()))))
                .flatMap(bookDto -> ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body( bookRepository
                        .save(bookMapper.fromBookDtoToBook(bookDto)).map(bookMapper::bookConvertToDto),BookDto.class)
                        .switchIfEmpty(notFound().build()));
//        return byId.flatMap(old ->
//                     ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
//                            .body(bookMono.map(book ->{
//                                old.setName(book.getName());
//                                old.setYear(book.getYear());
//                                old.setGenres(book.getGenres());
//                                old.setAuthor(book.getAuthor());
//                            return old;
//                            }).flatMap(bookRepository::save).map(bookMapper::bookConvertToDto), BookDto.class)
//                             .switchIfEmpty(notFound().build())
//                );

    }

    public Mono<ServerResponse> deleteBook(ServerRequest serverRequest){
        return bookRepository.findById(serverRequest.pathVariable("id")).flatMap(book ->
                ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(bookRepository.delete(book),Book.class)).switchIfEmpty(notFound().build());
    }
}
