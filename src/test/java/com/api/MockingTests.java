package com.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.controllers.APIController;
import com.api.entity.Person;
import com.api.repository.PeopleRepository;
import com.api.services.DataService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockingTests {

	//Checking Service and PeopleRepository integration
	@InjectMocks
	DataService data;
	
	@Mock
	PeopleRepository people;
	
	@InjectMocks
	APIController controller;
	
	@Mock
	DataService data2;
	
static	List<Person> mockData=new ArrayList<Person>();
	
	@BeforeAll
	public static void setup() {
		mockData.add(new Person(1,"Raj","Chennai"));
		mockData.add(new Person(2,"Ravi","Mumbai"));
	}
		
	@Test
	@DisplayName("Checking Service,Contoller and Repo Integration")
	public void testInstance() {
		assertThat(data).isNotNull();
		assertThat(people).isNotNull();
		assertThat(controller).isNotNull();
		assertThat(data2).isNotNull();
	}
	
	
	@Test
	@DisplayName("Checking Service and Repo Integration Flow")
	public void testFlow() {
		//doReturn(mockData).when(people).findAll();
		when(people.findAll()).thenReturn(mockData);
		assertThat(data.getPeople2()).hasSize(2);
		assertThat(data.getPeople2().get(1).getName()).isEqualTo("Ravi");
		verify(people,times(2)).findAll();
		
	}
	
	@Test
	@DisplayName("Checking Controller and Service integration flow")
	public void controlFlow()
	{
		when(data2.getPeople2()).thenReturn(mockData);
		assertThat(controller.people2())
		.hasSize(2)
		.hasOnlyElementsOfType(Person.class);
		 verify(data2,times(1)).getPeople2();
	}
}
