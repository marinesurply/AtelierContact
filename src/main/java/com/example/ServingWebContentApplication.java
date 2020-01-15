package com.example;

import com.example.model.Adresse;
import com.example.model.Contact;

import com.example.model.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;


@SpringBootApplication
public class ServingWebContentApplication {
	private static final Logger log = LoggerFactory.getLogger(ServingWebContentApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ServingWebContentApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		app.run(args);

	}
	@Bean
	public CommandLineRunner demo(ContactRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Contact("Jack", "Bauer"));
			repository.save(new Contact("Chloe", "O'Brian"));
			repository.save(new Contact("Kim", "Bauer"));
			repository.save(new Contact("David", "Palmer"));
			repository.save(new Contact("Michelle", "Dessler"));

			//repository.save(new Adresse("adresse 1 "));
			//repository.save(new Adresse("adresse 2 "));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Contact contact : repository.findAll()) {
				log.info(contact.toString());
			}
			log.info("");

		};
	}

	@SpringBootApplication
	public static class SecuringWebApplication {

		public static void main(String[] args) throws Throwable {
			SpringApplication.run(SecuringWebApplication.class, args);
		}

	}
}
