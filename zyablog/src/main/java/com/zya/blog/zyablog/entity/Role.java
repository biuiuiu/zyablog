package com.zya.blog.zyablog.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 角色bean
 * 
 * @author an
 *
 */
public class Role {
	private Long id;

	private String roleName;
	@JSONField(format = "yyyy-MM-dd:HH:mm:ss")
	private Date createDate;
	@JSONField(format = "yyyy-MM-dd:HH:mm:ss")
	private Date lastUpdateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
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
}