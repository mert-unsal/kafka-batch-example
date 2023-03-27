package com.munsal.kafkabatchexample;

import com.munsal.kafkaconfiguration.annotation.EnableKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableKafka
@EnableJpaRepositories
@SpringBootApplication
public class KafkaBatchExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBatchExampleApplication.class, args);
    }

}

