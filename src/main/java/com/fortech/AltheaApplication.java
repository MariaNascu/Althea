package com.fortech;

import com.fortech.entity.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AltheaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltheaApplication.class, args); }
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("http://localhost:3000","http://localhost:8080")
							.allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH","OPTIONS")
							.allowedHeaders("*")
							.allowCredentials(true);


				}
			};
		}

	}