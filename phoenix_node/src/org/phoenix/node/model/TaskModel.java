package org.phoenix.node.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.phoenix.node.dto.TaskStatusType;

@Entity
@Table(name="t_task")
public class TaskModel {
	
	private int id;
	private String taskType;
	private String taskName;
	private String taskData;
	private String slaveIP;
	private String taskParameter;
	private String message;
	private TaskStatusType taskStatusType;
	private Date startTime;
	private Date endTime;
	private int userId;
	public TaskModel() {
	}
	
	public TaskModel(String taskType, String taskName, String taskData,
			String slaveIP, String taskParameter,
			TaskStatusType taskStatusType, int userId) {
		super();
		this.taskType = taskType;
		this.taskName = taskName;
		this.taskData = taskData;
		this.slaveIP = slaveIP;
		this.taskParameter = taskParameter;
		this.taskStatusType = taskStatusType;
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
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskData() {
		return taskData;
	}
	public void setTaskData(String taskData) {
		this.taskData = taskData;
	}
	public String getSlaveIP() {
		return slaveIP;
	}
	public void setSlaveIP(String slaveIP) {
		this.slaveIP = slaveIP;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTaskParameter() {
		return taskParameter;
	}
	public void setTaskParameter(String taskParameter) {
		this.taskParameter = taskParameter;
	}
	@Enumerated(EnumType.STRING)
	public TaskStatusType getTaskStatusType() {
		return taskStatusType;
	}
	public void setTaskStatusType(TaskStatusType taskStatusType) {
		this.taskStatusType = taskStatusType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
