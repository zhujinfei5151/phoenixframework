package org.phoenix.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="l_web_batch")
public class BatchLogBean {
	private int id;
	private String batchId;
	public BatchLogBean() {
	}
	public BatchLogBean(String batchId) {
		super();
		this.batchId = batchId;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
}
