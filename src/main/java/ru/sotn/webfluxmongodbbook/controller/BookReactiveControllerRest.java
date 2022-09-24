package ru.sotn.webfluxmongodbbook.controller;

import org.springframework.context.annotation.Bean;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import ru.sotn.webfluxmongodbbook.controller.hadler.BookHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;



@Component
public class BookReactiveControllerRest {
    @Bean
    RouterFunction<ServerResponse> composeRoutes(BookHandler bookHandler){
        return route().GET("/react/book",accept(APPLICATION_JSON), bookHandler::getAllBook)

                .GET("/react/book/{id}", accept(APPLICATION_JSON),bookHandler::getBookById)

                .POST("/react/book", accept(APPLICATION_JSON), bookHandler::createBook)

                .PUT("react/book/update/{id}", accept(APPLICATION_JSON),bookHandler::updateBook)

                .DELETE("/react/book/delete/{id}", bookHandler::deleteBook)
                .build();
    }
}
