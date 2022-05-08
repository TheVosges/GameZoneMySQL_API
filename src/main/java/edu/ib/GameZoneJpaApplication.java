package edu.ib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class GameZoneJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameZoneJpaApplication.class, args);
    }

}
