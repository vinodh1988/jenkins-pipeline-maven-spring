package com.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Person;
import com.api.services.IDataService;

@RestController
@RequestMapping("/api")
public class APIController {
	
	@Autowired 
	IDataService service;
	
   @GetMapping("/greet")
   public String greet() {
      return "Spring boot is working";
   }
   
   @GetMapping("/static-people")
   public List<String> people() {
	   return service.getPeople();
   }      
   
   @GetMapping("/people")
   public List<Person> people2() {
	   return service.getPeople2();
   }  
   
   @PostMapping("/people")
   public ResponseEntity<Person> addPerson(@RequestBody Person p) {
	   try {
		   service.addPerson(p);
		   return new ResponseEntity(p,HttpStatus.CREATED);
	   }
	   catch (Exception e) {
		   return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	   }
		// TODO: handle exception
	}
   }

