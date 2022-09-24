package ru.sotn.webfluxmongodbbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import ru.sotn.webfluxmongodbbook.domain.Genre;
import ru.sotn.webfluxmongodbbook.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@SpringBootApplication
public class WebFluxMongoDbBookApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(WebFluxMongoDbBookApplication.class, args);
//		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		Iterator<Integer> iterator = list.iterator();
////		for(int i = 0; i<list.size(); ++i){
////			list.remove(1);
////		}
////
//		while (iterator.hasNext()){
//			if(iterator.next().equals(1)){
//				iterator.remove();
//			}
//		}
	}

}
