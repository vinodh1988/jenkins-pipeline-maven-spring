package com.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.services.DataService;
import com.api.services.IDataService;

@SpringBootTest
public class ServiceTests {
   @Autowired
   IDataService iservice;
   
   @Test
   @DisplayName("Autowired Service Check")
   public void instatiation() {
	   assertThat(iservice).isInstanceOf(DataService.class);
   }
   
   @Test
   @DisplayName("Testing Private Method")
   public void checkPrivateMethod() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	   Class now=DataService.class;
	   Method m=now.getDeclaredMethod("provideStrings");
	   m.setAccessible(true);
	  List<String> list=(List<String>) m.invoke(iservice);
	  assertThat(list).isNotNull();
   }
   
 
   
}
