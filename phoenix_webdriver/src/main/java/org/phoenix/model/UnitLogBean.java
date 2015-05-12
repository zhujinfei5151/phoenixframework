package org.phoenix.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="l_web_unit")
public class UnitLogBean {
	
	private int id;
	private String content;
	private String stepName;
	private String remark;
	private String status;
	private String screenShot;
	private CaseLogBean caseLogBean;
	
	public UnitLogBean() {
	}
	
	public UnitLogBean(String content, String stepName, String remark,
			String status, String screenShot, CaseLogBean caseLogBean) {
		super();
		this.content = content;
		this.stepName = stepName;
		this.remark = remark;
		this.status = status;
		this.screenShot = screenShot;
		this.caseLogBean = caseLogBean;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScreenShot() {
		return screenShot;
	}
	public void setScreenShot(String screenShot) {
		this.screenShot = screenShot;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="caseLogId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public CaseLogBean getCaseLogBean() {
		return caseLogBean;
	}
	public void setCaseLogBean(CaseLogBean caseLogBean) {
		this.caseLogBean = caseLogBean;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
}
