package com.optica.backend_crm;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BackendCrmApplication {

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
    }

	public static void main(String[] args) {
		SpringApplication.run(BackendCrmApplication.class, args);
	}

}
