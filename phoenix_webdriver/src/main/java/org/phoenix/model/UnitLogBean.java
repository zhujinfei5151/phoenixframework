package org.phoenix.model;

import javax.persistence.CascadeType;
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
	private String remark;
	private String screenShot;
	private CaseLogBean caseLogBean;
	
	public UnitLogBean() {
	}
	public UnitLogBean(String content, String remark, String screenShot,
			CaseLogBean caseLogBean) {
		super();
		this.content = content;
		this.remark = remark;
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
	@ManyToOne(cascade={CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name="caseLogId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public CaseLogBean getCaseLogBean() {
		return caseLogBean;
	}
	public void setCaseLogBean(CaseLogBean caseLogBean) {
		this.caseLogBean = caseLogBean;
	}
}
