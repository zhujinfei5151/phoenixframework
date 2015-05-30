package org.phoenix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_data")
public class TestDataBean implements SuperDataBean{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String locatorName;
	private String locatorData;
	private String locatorType;
	
	public TestDataBean() {
	}	

	public TestDataBean(String locatorName, String locatorData,
			String locatorType) {
		super();
		this.locatorName = locatorName;
		this.locatorData = locatorData;
		this.locatorType = locatorType;
	}


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocatorName() {
		return locatorName;
	}

	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}

	public String getLocatorData() {
		return locatorData;
	}

	public void setLocatorData(String locatorData) {
		this.locatorData = locatorData;
	}

	public String getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}

	@Override
	public void say() {
		System.out.println("test...");
	}	
	
}
