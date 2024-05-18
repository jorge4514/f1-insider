package com.example.f1_insider_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.example.f1_insider_api.Laps.RestTemplateConfig;

@SpringBootApplication
@Import(RestTemplateConfig.class)
public class F1InsiderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(F1InsiderApiApplication.class, args);
	}

}
