package ru.sotn.webfluxmongodbbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;




@Document("genres")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
//@CompoundIndexes({
//
//        @CompoundIndex( def = "{'name' : 1}", unique = true)
//})
public class Genre {


    @Id
    private String idGenre;


    @Field("name")
    private String nameGenre;

    public Genre(String nameGenre) {
        this.nameGenre = nameGenre;
    }
}
