package com.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.entity.Person;
import com.api.test.utils.TestUtils;

@SpringBootTest
public class EntityClassTests {
	
	@Test
	@DisplayName("Test Fields")
	public void testPerson() {
		List<String> list=TestUtils.getFields(Person.class);
		assertThat(list.get(0)).isEqualTo("sno");
		assertThat(list.get(1)).isEqualTo("name");
		assertThat(list.get(2)).isEqualTo("city");
		
	}
	
	@Test
	@DisplayName("Test Types")
	public void testPersonTypes() {
		List<String> list=TestUtils.getFieldTypes(Person.class);
		assertThat(list.get(0)).isEqualTo("Integer");
		assertThat(list.get(1)).isEqualTo("String");
		assertThat(list.get(2)).isEqualTo("String");
		
	}

}
