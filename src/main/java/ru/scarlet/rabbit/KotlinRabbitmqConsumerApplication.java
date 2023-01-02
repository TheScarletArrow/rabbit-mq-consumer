package ru.scarlet.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class KotlinRabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KotlinRabbitmqConsumerApplication.class, args);
	}

}
