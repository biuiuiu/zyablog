package com.zya.blog.zyablog.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
	private Long id;

	private String name;

	private Integer age;

	private String phone;

	private String email;

	@JSONField(format = "yyyy-MM-dd : HH:mm:ss")
	private Date createDate;

	private Date lastUpdateDate;

	private Long createUser;

	private Long lastUpdateUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(Long lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
}