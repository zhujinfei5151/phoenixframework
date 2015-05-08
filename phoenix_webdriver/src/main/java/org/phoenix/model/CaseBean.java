package org.phoenix.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="t_web_case")
@BatchSize(size=30)
public class CaseBean {
	
	private int id;
	private String caseName;
	private String codeContent;
	private String remark;
	private int status = 1;
	private Date createDate;
	private ScenarioBean scenarioBean;
	private int userId;
	private Set<LocatorBean> locatorBeans;
	
	public CaseBean() {
		// TODO Auto-generated constructor stub
	}
	
	public CaseBean(String caseName, String codeContent, String remark ) {
		super();
		this.caseName = caseName;
		this.codeContent = codeContent;
		this.remark = remark;
	}
	
	public CaseBean(String caseName, String remark, int status, Date createDate,
			ScenarioBean scenarioBean, int userId) {
		super();
		this.caseName = caseName;
		this.remark = remark;
		this.status = status;
		this.createDate = createDate;
		this.scenarioBean = scenarioBean;
		this.userId = userId;
	}

	public CaseBean(String caseName, String codeContent, String remark, int status ,Date createDate,
			ScenarioBean scenarioBean, int userId) {
		super();
		this.caseName = caseName;
		this.codeContent = codeContent;
		this.remark = remark;
		this.status = status;
		this.createDate = createDate;
		this.scenarioBean = scenarioBean;
		this.userId = userId;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="scenarioId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public ScenarioBean getScenarioBean() {
		return scenarioBean;
	}
	public void setScenarioBean(ScenarioBean scenarioBean) {
		this.scenarioBean = scenarioBean;
	}
	@OneToMany(mappedBy="caseBean",targetEntity=LocatorBean.class,cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(FetchMode.SUBSELECT)
	public Set<LocatorBean> getLocatorBeans() {
		return locatorBeans;
	}

	public void setLocatorBeans(Set<LocatorBean> locatorBeans) {
		this.locatorBeans = locatorBeans;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	
	@Column(name="codeContent",length=65500,nullable=true)
	public String getCodeContent() {
		return codeContent;
	}

	public void setCodeContent(String codeContent) {
		this.codeContent = codeContent;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
