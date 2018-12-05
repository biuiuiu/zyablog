package com.zya.blog.zyablog.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户bean
 * 
 * @author an
 *
 */
public class User {

	public User() {
	}

	public User(String acount, String name) {
		this.acount = acount;
		this.name = name;
	}

	private Long id;

	private String acount;

	private String name;

	private Boolean sex;

	private String password;

	private String lover;

	private String phone;

	@JSONField(format = "yyyy-MM-dd:HH:mm:ss")
	private Date createDate;

	@JSONField(format = "yyyy-MM-dd:HH:mm:ss")
	private Date lastUpdateDate;

	private Long roleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAcount() {
		return acount;
	}

	public void setAcount(String acount) {
		this.acount = acount == null ? null : acount.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getLover() {
		return lover;
	}

	public void setLover(String lover) {
		this.lover = lover == null ? null : lover.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}