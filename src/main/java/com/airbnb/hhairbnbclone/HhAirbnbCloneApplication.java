package com.airbnb.hhairbnbclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HhAirbnbCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HhAirbnbCloneApplication.class, args);
	}

}
