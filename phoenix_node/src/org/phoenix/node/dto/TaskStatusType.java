package org.phoenix.node.dto;

public enum TaskStatusType {
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
