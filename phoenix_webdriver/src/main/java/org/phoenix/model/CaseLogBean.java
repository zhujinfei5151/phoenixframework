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
@Table(name="l_web_case")
public class CaseLogBean {
	
	private int id;
	private String actor;
	private String clientIP;
	private String EngineType;
	private String StartTime;
	private String EndTime;
	private String duration;
	private String status;
	private CaseBean caseBean;
	private BatchLogBean batchLogBean;
	
	public CaseLogBean() {
	}
	
	public CaseLogBean(String actor, String clientIP, String engineType,
			String startTime, String endTime, String duration, String status,
			CaseBean caseBean, BatchLogBean batchLogBean) {
		super();
		this.actor = actor;
		this.clientIP = clientIP;
		EngineType = engineType;
		StartTime = startTime;
		EndTime = endTime;
		this.duration = duration;
		this.status = status;
		this.caseBean = caseBean;
		this.batchLogBean = batchLogBean;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getEngineType() {
		return EngineType;
	}
	public void setEngineType(String engineType) {
		EngineType = engineType;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne(cascade={CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinColumn(name="caseId")
	@LazyCollection(LazyCollectionOption.FALSE)
	public CaseBean getCaseBean() {
		return caseBean;
	}
	public void setCaseBean(CaseBean caseBean) {
		this.caseBean = caseBean;
	}
    @ManyToOne(cascade={CascadeType.REMOVE})
    @JoinColumn(name="batchLogId")
    @LazyCollection(LazyCollectionOption.FALSE)
	public BatchLogBean getBatchLogBean() {
		return batchLogBean;
	}

	public void setBatchLogBean(BatchLogBean batchLogBean) {
		this.batchLogBean = batchLogBean;
	}
}
