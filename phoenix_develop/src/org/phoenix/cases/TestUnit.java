package org.phoenix.cases;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.phoenix.model.TestEnum;

public class TestUnit {
	@Test
	public void test01(){
		TestEnum[] t = TestEnum.values();
		for(TestEnum te : t){
			System.out.println(te.name());
		}
	}
	@Test
	public void test02(){
		try{
			
			Assert.assertFalse(false);
		}catch(AssertionError e){
			System.out.println("11"+e.getMessage());
		}
	}

}
