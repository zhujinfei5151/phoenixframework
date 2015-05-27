package org.phoenix.web.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="t_user")
@BatchSize(size=30)
public class User {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private int role;
	private String roleName;
	private String email;
	private Date createDate;
	private Set<TaskModel> taskModels;
	
	public User() {
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public User(int id, String nickname) {
		super();
		this.id = id;
		this.nickname = nickname;
	}
	@Email(message="邮箱格式不正确")
	@NotBlank(message="邮箱不能为空")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
    
	@OneToMany(mappedBy="user",targetEntity=TaskModel.class,cascade={CascadeType.ALL},orphanRemoval=true)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.SUBSELECT)
	public Set<TaskModel> getTaskModels() {
		return taskModels;
	}

	public void setTaskModels(Set<TaskModel> taskModels) {
		this.taskModels = taskModels;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	@Column(unique=true,nullable=false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotEmpty(message="用户名不能为空")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotEmpty(message="用户密码不能为空")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(unique=true,nullable=false)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}	
	
}
