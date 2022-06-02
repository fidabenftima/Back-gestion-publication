package com.example.demo.model;


import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;


@Document(collection = "publications")
public class Publication {
	

	  @Id
	  private String id;
  
	  private String title;
	  private String description;

	  public Publication() {

	  }

	  public Publication(String title, String description) {
	    this.title = title;
	    this.description = description;
	  }

	  public String getId() {
	    return id;
	  }

	  public String getTitle() {
	    return title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

	  public String getDescription() {
	    return description;
	  }

	  public void setDescription(String description) {
	    this.description = description;
	  }


	  @Override
	  public String toString() {
	    return "Publication [id=" + id + ", title=" + title + ", desc=" + description +  "]";
	  }
	}