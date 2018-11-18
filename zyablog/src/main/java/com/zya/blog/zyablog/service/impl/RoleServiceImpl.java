package com.zya.blog.zyablog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zya.blog.zyablog.dao.RoleDao;
import com.zya.blog.zyablog.entity.Role;
import com.zya.blog.zyablog.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao dao;

	@Override
	public Role getRoleById(Long id) {
		if (id == null) {
			return null;
		}
		return dao.getRoleById(id);
	}

}
