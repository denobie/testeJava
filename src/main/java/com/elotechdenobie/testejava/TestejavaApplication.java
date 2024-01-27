package com.elotechdenobie.testejava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TestejavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestejavaApplication.class, args);
	}

}
