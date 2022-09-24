package ru.sotn.webfluxmongodbbook.repository.genre;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Genre;

import java.util.List;
import java.util.Optional;



public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Genre> getByNameOrCreate(Genre genre) {
//        TypedQuery<Genre> query = em.createQuery("SELECT g FROM Genre g WHERE g.nameGenre = :name", Genre.class);
//        query.setParameter("name", genre.getNameGenre());
//        List<Genre> genres = query.getResultList();
//        if(!genres.isEmpty()){
//            return Optional.of(genres.get(0));
//        }
//        else {
//            return Optional.of(em.merge(genre));
//        }



        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(genre.getNameGenre()));
        Flux<Genre> genres = mongoTemplate.find(query, Genre.class);
        return Mono.from(genres).switchIfEmpty(mongoTemplate.save(genre));
//        if (!genres.isEmpty()) {
//            return Optional.of(genres.get(0));
//        } else {
//            return Optional.of(mongoTemplate.save(genre));
//        }
    }
}
