package by.bookify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BookifyCoreApp {

    public static void main(String[] args) {
        SpringApplication.run(BookifyCoreApp.class, args);
    }
}
