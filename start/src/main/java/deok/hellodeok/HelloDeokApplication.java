package deok.hellodeok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @Component를 포함하는 어노테이션은 자동으로 스프링 빈에 붙게됨
// @Service, @Repository, @Controller

@SpringBootApplication
public class HelloDeokApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloDeokApplication.class, args);
    }

}
