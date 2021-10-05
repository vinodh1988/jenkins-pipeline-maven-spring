package com.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.controllers.APIController;
import com.api.entity.Person;
import com.api.services.IDataService;

@SpringBootTest
@ExtendWith(EasyMockExtension.class)
public class EasyMockIntegrationTests {
@TestSubject
   APIController api;

@Mock
   IDataService service;

static List<Person> list=new ArrayList<Person>();

@BeforeAll
public static void setup()
{
   list.add(new Person(1,"Raj","Kanpur"));
   list.add(new Person(4,"Vivek","Mumbai"));
}

@Test
@DisplayName("Easy Mocking")
public void testEasy() {
	EasyMock.expect(service.getPeople2()).andReturn(list);
	EasyMock.replay(service);
	assertEquals(api.people2(),list);
}

}
