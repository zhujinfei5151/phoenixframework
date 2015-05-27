package org.phoenix.utils;

public enum TaskStatusType {
	 NOT_RUNNING("NOT_RUNNING"),
     RUNNING("RUNNING"),
     SUCCESS("SUCCESS"),
     FAIL("FAIL"),
     STOP("STOP"),
     WAITING("WAITING");
     
 	private String name;
 	private TaskStatusType(String name) {
 		this.name = name;
 	}

 	public String getName() {
 		return name;
 	}

 	public void setName(String name) {
 		this.name = name;
 	}
}
