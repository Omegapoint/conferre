package se.omegapoint.conferre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.omegapoint.conferre.event.EventBus;

@SpringBootApplication
public class ConferreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferreApplication.class, args);
	}

	@Bean
	public EventBus getEventBus() {
		return new EventBus();
	}
}
