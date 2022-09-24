package ru.sotn.webfluxmongodbbook.repository.book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import ru.sotn.webfluxmongodbbook.domain.Book;

import java.util.List;


public class BookRepositoryCustomImpl implements BookRepositoryCustom{


    @Autowired
    ReactiveMongoTemplate mongoTemplate;

//    @Override
//    public List<Book> getAllWithComment() {
//        List<Book> books = em.createQuery("SELECT DISTINCT b FROM Book b " +
//                                "LEFT JOIN FETCH b.comments bc "
//                        , Book.class)
//                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//                .getResultList();
//
//        books =  em.createQuery("SELECT DISTINCT b from Book b " +
//                        "LEFT JOIN FETCH b.genres bg "+
//                        "LEFT JOIN FETCH b.author ba " +
//                        "WHERE b in :books", Book.class)
//                .setParameter("books", books)
//                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//                .getResultList();
//
//        return null;
//    }

//    @Override
//    public boolean isExistBook(Book book) {
////        TypedQuery<Book> query = em.createQuery("SELECT DISTINCT b FROM Book b " +
////                "LEFT JOIN FETCH b.genres "+
////                "LEFT JOIN FETCH b.author " +
////                "WHERE b.name = :name_book AND b.author.nameAuthor = :name_author", Book.class);
////        query.setParameter("name_author",book.getAuthor().getNameAuthor());
////        query.setParameter("name_book",book.getName());
////        List<Book> books = query.getResultList();
//
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is(book.getName()));
//        query.addCriteria(Criteria.where("author.name").is(book.getAuthor().getNameAuthor()));
//        Flux<Book> books = mongoTemplate.find(query, Book.class);
//
////        List<Book> books = em.createQuery("SELECT DISTINCT b FROM Book b " +
////                                "LEFT JOIN FETCH b.comments bc "
////                        , Book.class)
////                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
////                .getResultList();
////
////        books =  em.createQuery("SELECT DISTINCT b from Book b " +
////                        "LEFT JOIN FETCH b.genres bg "+
////                        "LEFT JOIN FETCH b.author ba " +
////                        "WHERE b in :books AND bg.author.nameAuthor = :name_author AND b.name_book = :name_book", Book.class)
////                .setParameter("books", books)
////                .setParameter("name_author",book.getAuthor().getNameAuthor())
////                .setParameter("name_book",book.getName())
////                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
////                .getResultList();
//
//        return !books.isEmpty();
//
//    }
}
