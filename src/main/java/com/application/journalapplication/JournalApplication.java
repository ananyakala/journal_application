package com.application.journalapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;

@SpringBootApplication
public class JournalApplication {

	// public static void main(String[] args) {
	// SpringApplication.run(JournalApplication.class, args);
	// }

	// }

	public static void main(String[] args) { // defining the port
		SpringApplication app = new SpringApplication(JournalApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
		app.run(args);
	}
}
