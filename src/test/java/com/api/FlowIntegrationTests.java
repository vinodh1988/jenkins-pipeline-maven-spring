package com.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.controllers.APIController;
import com.api.services.IDataService;

@SpringBootTest
public class FlowIntegrationTests {
   @Autowired
   APIController first;
   
  
   @Autowired
   IDataService data;
   
   @Test
   @DisplayName("Testing People Function")
   public void getPeopleTest() {
	   List<String> list=new ArrayList<String>();
		  list.add("Rajan");

	  assertThat(first.people()).hasSizeGreaterThanOrEqualTo(1);

	  
   }
   
  
}
