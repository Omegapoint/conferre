package se.omegapoint.conferre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:8080")
						.allowedMethods("GET","POST","PUT")
						.maxAge(3600);
			}
		};
	}
}
