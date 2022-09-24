package ru.sotn.webfluxmongodbbook.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Document("authors")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
//@CompoundIndexes({
//
//        @CompoundIndex( def = "{'name' : 1}", unique = true)
//})
public class Author {
    @Id
    private String idAuthor;


    @Field("name")
    private String nameAuthor;

    @Field("year")
    private String year;

    public Author(String nameAuthor, String year) {
        this.nameAuthor = nameAuthor;
        this.year = year;
    }
}
