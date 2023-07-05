package by.bookify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BookifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookifyApplication.class, args);
    }

}
