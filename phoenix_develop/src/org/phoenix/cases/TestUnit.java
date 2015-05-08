package org.phoenix.cases;

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

}
