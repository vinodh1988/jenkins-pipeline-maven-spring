package com.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.controllers.APIController;

@SpringBootTest
class SpringBootUnitIntegrationTestingApplicationTests {
	
	@Autowired
	APIController controller;
	
	@Autowired
	APIController controller2;
	
	Class<APIController> api=APIController.class;

	@Test
	@DisplayName("APIControlled class must be instantiated as singleon")
	void controllerExistsAndAutowired() {
		 assertNotNull(controller);
		 assertNotNull(controller2);
		 assertEquals(controller, controller2);
		 
	}
	
	@Test
	@DisplayName("Asserting whether it is RestController and has root path specified")
	public void decoratedCorrectly() {
		
		Annotation []list =api.getAnnotations();
		
		assertEquals(list.length, 2);
		assertEquals(list[0].annotationType().getSimpleName(),"RestController");
		assertEquals(list[1].annotationType().getSimpleName(),"RequestMapping");
	
	}
	 
	@Test
	@DisplayName("Asserting too path to be api")
	public void checkUrlPattern() {
		RequestMapping rm=api.getAnnotation(RequestMapping.class);
		assertEquals(rm.value()[0],"/api");
		assertThat(rm.value().length).isEqualTo(1);
	}
     
}
