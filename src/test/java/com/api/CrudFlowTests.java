package com.api;



import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.entity.Person;
import com.api.repository.PeopleRepository;
import com.api.services.DataService;
import com.api.utils.RecordAlreadyExistsException;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class CrudFlowTests {
  
	@InjectMocks
	DataService data;
	
	@Mock
	PeopleRepository people;
	
	public static int  testCount;
    static List<Person> list=new ArrayList<Person>();
	
	public void mock() {
		System.out.println("inside"+testCount);

	

		doAnswer((i)->{
		
			return null;
		}).when(people).findBySno(any(Integer.class));
	    
	    doAnswer(x->{
	    	
	    	list.add(x.getArgument(0));
	    	System.out.println(list);
	    	return null;
	    }).when(people).save(any(Person.class));
	}
	
	@ParameterizedTest
	@DisplayName("Data Driven Test")
	@CsvFileSource(resources = "/sample.csv",numLinesToSkip = 1)
	@Order(1)
	public void controlTest(Integer sno, String name,String city) {
	
		testCount++;
		mock();
		System.out.println(testCount);
	

			assertDoesNotThrow(()->data.addPerson(new Person(sno,name,city)));
	}
	
	@Test
    @DisplayName("Checking insertion ")
	@Order(2)
	public void Test() {
		assertEquals(list.size(), 5);
	}
	
	@Test
	@DisplayName("Checking ")
	@Order(3)
	public void OneMoreInsertion() throws RecordAlreadyExistsException {
		DataService data=Mockito.mock(DataService.class);
		doAnswer(
				i->{
					Person p=i.getArgument(0);
					if(p.getSno()==1 && list.size()==5)
						throw new RecordAlreadyExistsException(1);
					return null;
				}).when(data).addPerson(any(Person.class));
		assertThrows(RecordAlreadyExistsException.class,()->
		data.addPerson(new Person(1,"Rahul","Mumbai")));
	}
	
}
