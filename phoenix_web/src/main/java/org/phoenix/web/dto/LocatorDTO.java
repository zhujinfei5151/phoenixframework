package org.phoenix.web.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.constraints.NotBlank;
import org.phoenix.action.LocatorType;

public class LocatorDTO {
	private int id;
	private int caseId;
	private String locatorDataName;
	private String locatorData;
	private LocatorType locatorType;
	public LocatorDTO() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	@NotBlank(message="定位信息的标识不能为空")
	public String getLocatorDataName() {
		return locatorDataName;
	}
	public void setLocatorDataName(String locatorDataName) {
		this.locatorDataName = locatorDataName;
	}
	@NotBlank(message="定位信息的数据不能为空")
	public String getLocatorData() {
		return locatorData;
	}
	public void setLocatorData(String locatorData) {
		this.locatorData = locatorData;
	}
	@Enumerated(EnumType.STRING)
	public LocatorType getLocatorType() {
		return locatorType;
	}
	public void setLocatorType(LocatorType locatorType) {
		this.locatorType = locatorType;
	}
}
