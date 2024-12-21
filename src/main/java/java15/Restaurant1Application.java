package java15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "java15.repo")
public class Restaurant1Application {

    public static void main(String[] args) {
        SpringApplication.run(Restaurant1Application.class, args);
    }

}
