package com.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.entity.Person;
import com.api.services.DataService;
import com.api.services.IDataService;
import com.api.utils.RecordAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceWithExceptionTests {
	
	@Autowired
    IDataService data;

	static Person one,two;
	
	@BeforeAll
	public static void setup()
	{
	   one=new Person(1,"Raj","Kanpur");
	   two=new Person(4,"Vivek","Mumbai");
	}
	@Test
	@DisplayName("Testing the injection and function availability")
	public void testAvailaibility() throws RecordAlreadyExistsException {
		 IDataService obj= spy(data);
		 Person person=spy(one);
		 Person person2=spy(two);
		 System.out.println(person2+" "+two);
		 
		 doAnswer(i->{
			Person p= (Person)i.getArgument(0);
			System.out.println(p.getName());
			if(p.getSno()<=0)
				throw new Exception();
			if(p.getSno()==1 || p.getSno()==2)
				throw new RecordAlreadyExistsException(p.getSno());
			
			   return null;
		 }).when(obj).addPerson(person2);
		 
		doThrow(new RecordAlreadyExistsException(person.getSno()))
		.when(obj).addPerson(person);
		 
		 assertThat(obj).isInstanceOf(DataService.class);
		RecordAlreadyExistsException e= assertThrows(RecordAlreadyExistsException.class, ()->{
			 obj.addPerson(person);
		 });
		
	     assertEquals(e.getMessage(),"The record with this key 1 already exists");
	     assertDoesNotThrow(()->obj.addPerson(person2));
	     person2.setSno(1);
	     assertThrows(RecordAlreadyExistsException.class, ()->obj.addPerson(person2));
	}
}
