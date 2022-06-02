package com.example.demo.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.PublicationRepository;
import com.example.demo.model.Publication;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class PublicationRest {
	@Autowired
	  PublicationRepository publicationRepository;
	  

	  @GetMapping("/publications")
	  public ResponseEntity<List<Publication>> getAllPublications(@RequestParam(required = false) String title) {
	    try {
	      List<Publication> publications = new ArrayList<Publication>();

	      if (title == null)
	    	  publicationRepository.findAll().forEach(publications::add);
	      else
	    	  publicationRepository.findByTitleContaining(title).forEach(publications::add);

	      if (publications.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(publications, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/publications/{id}")
	  public ResponseEntity<Publication> getPublicationById(@PathVariable("id") String id) {
	    Optional<Publication> publicationData = publicationRepository.findById(id);

	    if (publicationData.isPresent()) {
	      return new ResponseEntity<>(publicationData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/publications")
	  public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
	    try {
	    	Publication _publication = publicationRepository.save(new Publication(publication.getTitle(), publication.getDescription()));
	      return new ResponseEntity<>(_publication, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @PutMapping("/publications/{id}")
	  public ResponseEntity<Publication> updatePublication(@PathVariable("id") String id, @RequestBody Publication publication) {
	    Optional<Publication> publicationData = publicationRepository.findById(id);

	    if (publicationData.isPresent()) {
	    	Publication _publication = publicationData.get();
	      _publication.setTitle(publication.getTitle());
	      _publication.setDescription(publication.getDescription());
	      return new ResponseEntity<>(publicationRepository.save(_publication), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/publications/{id}")
	  public ResponseEntity<HttpStatus> deletePublication(@PathVariable("id") String id) {
	    try {
	    	publicationRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping("/publications")
	  public ResponseEntity<HttpStatus> deleteAllPublications() {
	    try {
	    	publicationRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


	}