package org.phoenix.node.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_slave")
public class SlaveModel {
	
	private int id;
	private String slaveIP;
	private String remark;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSlaveIP() {
		return slaveIP;
	}
	public void setSlaveIP(String slaveIP) {
		this.slaveIP = slaveIP;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
