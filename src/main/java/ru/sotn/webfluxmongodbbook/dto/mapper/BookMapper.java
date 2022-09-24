package ru.sotn.webfluxmongodbbook.dto.mapper;

import org.springframework.stereotype.Component;
import ru.sotn.webfluxmongodbbook.domain.Author;
import ru.sotn.webfluxmongodbbook.domain.Book;
import ru.sotn.webfluxmongodbbook.domain.Genre;
import ru.sotn.webfluxmongodbbook.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    public Book fromBookDtoToBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(bookDto.getIdBook());
        book.setName(bookDto.getNameBook());
        book.setYear(bookDto.getYearBook());
        book.setAuthor(new Author(bookDto.getNameAuthor(), bookDto.getYearAuthor()));
        String[] names = bookDto.getNamesGenre().split(", ");
        List<Genre> genres = new ArrayList<>();
        for (String str: names) {
            genres.add(new Genre(str));
        }

        book.setGenres(genres);
        return book;
    }
    public BookDto bookConvertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setIdBook(book.getId());
        bookDto.setNameBook(book.getName());
        bookDto.setYearBook(book.getYear());
        bookDto.setNameAuthor(book.getAuthor().getNameAuthor());
        bookDto.setYearAuthor(book.getAuthor().getYear());


        if(book.getGenres().isEmpty()){
            bookDto.setNamesGenre(null);
        }
        else bookDto.setNamesGenre(book.getGenres().stream().map(Genre::getNameGenre).collect(Collectors.joining(", ")));

        return bookDto;
    }
}
