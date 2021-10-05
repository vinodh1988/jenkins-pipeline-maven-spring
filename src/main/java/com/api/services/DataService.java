package com.api.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.Person;
import com.api.repository.PeopleRepository;
import com.api.utils.RecordAlreadyExistsException;

@Service
public class DataService implements IDataService {
   @Autowired
   PeopleRepository people;

   private List<String> provideStrings(){
	   String[] strings= {"Raja","Ram","Rahim","Ajay","Krishna","Jerry"};
	   return  Arrays.asList(strings);
   }

     @Override
    public List<String> getPeople() {
	// TODO Auto-generated method stub
	     return provideStrings();
    }
   
     @Override
     public List<Person> getPeople2() {
 	// TODO Auto-generated method stub
 	     return people.findAll();
     }

	@Override
	public void addPerson(Person person) throws RecordAlreadyExistsException {
		// TODO Auto-generated method stub
	        Person p=people.findBySno(person.getSno());
	        if(p==null) {
	        	people.save(person);
	        }
	        else
	        	throw new RecordAlreadyExistsException(person.getSno());
		
	}
   
    
}
