package com.latewind.test.study;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Test;

public class TestStudy {

	@Test
	public void test() {
		Integer var1=1200;
		Integer var2=1200;
		assertEquals(false, var1==var2);
	}
	
	public void testMapForEach(){
		
		HashMap<String, Object> testMap=new HashMap<>();
		testMap.entrySet();
		
	}

}
