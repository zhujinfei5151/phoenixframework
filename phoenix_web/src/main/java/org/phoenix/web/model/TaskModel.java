package org.phoenix.web.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.phoenix.web.dto.TaskStatusType;
import org.phoenix.web.dto.TaskType;

@Entity
@Table(name="t_task")
public class TaskModel {
	
	private int id;
	private TaskType taskType;
	private String taskName;
	private String taskData;
	private String slaveIP;
	private String taskParameter;
	private String message;
	private TaskStatusType taskStatusType;
	private Date startTime;
	private Date endTime;
	private User user;
	public TaskModel() {
	}
	
	public TaskModel(TaskType taskType, String taskName, String taskData,
			String slaveIP, String taskParameter,
			TaskStatusType taskStatusType, User user) {
		super();
		this.taskType = taskType;
		this.taskName = taskName;
		this.taskData = taskData;
		this.slaveIP = slaveIP;
		this.taskParameter = taskParameter;
		this.taskStatusType = taskStatusType;
		this.user = user;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Enumerated(EnumType.STRING)
	public TaskType getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
	@NotBlank(message="任务名称不能为空")
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@NotBlank(message="需要执行的场景或用例不能为空")
	public String getTaskData() {
		return taskData;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
