package org.phoenix.web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

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
	@NotBlank(message="IP地址不能为空，端口形式如：192.168.1.111:8080,80端口无需添加")
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
