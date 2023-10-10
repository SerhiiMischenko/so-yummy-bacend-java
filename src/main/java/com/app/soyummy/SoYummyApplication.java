package com.app.soyummy;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoYummyApplication {

	public static void main(String[] args) {
		Flyway flyway = Flyway.configure()
				.dataSource("jdbc:mysql://localhost:3306", "root", "Lolka911")
				.defaultSchema("soymmyDB")
				.load();
		flyway.migrate();
		SpringApplication.run(SoYummyApplication.class, args);
	}

}
