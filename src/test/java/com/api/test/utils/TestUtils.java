package com.api.test.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.api.entity.Person;

public class TestUtils {
   public static List<String> getFields(Class classname) {
	   Field[] fields=classname.getDeclaredFields();
	  
	   List<String> list=new ArrayList<String>();
	   for(Field x:fields)
		   list.add(x.getName());
	   return list;
   }
   
   
   public static List<String> getFieldTypes(Class classname) {
	   Field[] fields=classname.getDeclaredFields();
	  
	   List<String> list=new ArrayList<String>();
	   for(Field x:fields)
		   list.add(x.getType().getSimpleName());
	   return list;
   }
   
   
  
}
