package com.codeneeti.technexushub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@SpringBootApplication
@RestController
public class TechNexusHubApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(TechNexusHubApplication.class, args);
	}
	@GetMapping("/hi")
	public String welcomeMsg(){
		System.out.println("coming inside");
		return "Welcome back";
	}
@Autowired
private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("Kabeer@12"));
	}
}
