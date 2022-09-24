package ru.sotn.webfluxmongodbbook.repository.author;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.sotn.webfluxmongodbbook.domain.Author;

import java.util.List;
import java.util.Optional;


public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{
//    @PersistenceContext
//    private EntityManager em;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Author> getByNameOrCreate(Author author) {
//        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.nameAuthor = :name", Author.class);
//        query.setParameter("name", author.getNameAuthor());
//        List<Author> authors = query.getResultList();
//
//

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(author.getNameAuthor()));
        Flux<Author> authors = mongoTemplate.find(query,Author.class);
        return Mono.from(authors).switchIfEmpty(mongoTemplate.save(author));


//        if (!authors.isEmpty()) {
//            return Optional.of( authors.get(0));
//        } else {
//            return Optional.of( mongoTemplate.save(author));
//        }
    }
}
