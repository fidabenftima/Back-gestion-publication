package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.DAO.PublicationRepository;
import com.example.demo.model.Publication;


@SpringBootApplication
public class FirstMongoAppApplication  implements
ApplicationRunner{
	@Autowired
	private PublicationRepository publicationRepository;
	public static void main(String[] args) {
		SpringApplication.run(FirstMongoAppApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
	
		Publication publication1 = new Publication("Bonjour c'est une belle journée", "Fida Ben Ftima");
		Publication publication2 = new Publication("dalton", "jack");
		publicationRepository.saveAll(Arrays.asList(publication1, publication2));		
	}

}
