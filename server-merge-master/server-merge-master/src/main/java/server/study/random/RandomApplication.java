package server.study.random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import server.study.random.domain.Member;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RandomApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomApplication.class, args);
    }

}
