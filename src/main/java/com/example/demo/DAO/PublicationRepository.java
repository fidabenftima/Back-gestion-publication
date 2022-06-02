package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Publication;
@RestController

public interface PublicationRepository extends MongoRepository<Publication, String>{
	
	  List<Publication> findByTitleContaining(String title);
}
