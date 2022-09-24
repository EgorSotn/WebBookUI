package ru.sotn.webfluxmongodbbook.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
import java.util.Collections;
import java.util.List;


@Document("books")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@CompoundIndexes({
        @CompoundIndex(name = "name_author", def = "{'author.name' : 1, 'name': 1}", unique = true),
//        @CompoundIndex( def = "{'genres.name' : 1}", unique = false),
//        @CompoundIndex( def = "{'author.name' : 1}", unique = false)
})
public class Book implements Serializable {

    @Id
    private String id;
    @Field("name")
    private String name;
    @Field("year")
    private String year;
    @Field("genres")
    private List<Genre> genres;
    @Field("author")
    private Author author;

    @DBRef
    private List<Comment> comments;

    public Book(String id, String name, String year, Genre genre, Author author, Comment comments) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genres = Collections.singletonList(genre);
        this.author = author;
        this.comments = Collections.singletonList(comments);
    }

    public Book(String id, String name, String year, Genre genre, Author author) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.genres = Collections.singletonList(genre);
        this.author = author;
    }

    public Book(String name, String year, List<Genre> genres, Author author, List<Comment> comments) {
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.author = author;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                ", author=" + author +
                '}';
    }
}
