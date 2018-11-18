package com.zya.blog.zyablog.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zya.blog.zyablog.dao.UserDao;
import com.zya.blog.zyablog.entity.User;
import com.zya.blog.zyablog.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao dao;

	@Override
	public User getUserByAcount(String acount) {
		if (StringUtils.isBlank(acount)) {
			return null;
		}
		return dao.getUserByAcount(acount);
	}

}
