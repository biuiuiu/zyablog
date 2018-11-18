package com.zya.blog.zyablog.service;

import com.zya.blog.zyablog.entity.User;

public interface UserService {

	/**
	 * 根据用户查询用户信息
	 * 
	 * @param acount
	 * @return
	 */
	User getUserByAcount(String acount);
}
