package com.example.marvel_workshop39;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.marvel_workshop39.service.MarvelService;

@SpringBootApplication
public class MarvelWorkshop39Application implements CommandLineRunner {

	@Autowired
	private MarvelService svc;
	public static void main(String[] args) {
		SpringApplication.run(MarvelWorkshop39Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(svc.getByMarvelId(1009243));
		// this.svc.getName("fa", 20, 0);
		
	}

}
