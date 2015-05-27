package org.phoenix.model;

public enum TestEnum {
	Test1("Test1"),
	Test2("Test2"),
	Test3("Test3");
	
	private String name;
	private TestEnum(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
