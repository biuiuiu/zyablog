package com.zya.blog.zyablog.dao;


import org.apache.ibatis.annotations.Mapper;

import com.zya.blog.zyablog.entity.User;

@Mapper
public interface UserDao {
	
	User getList();
}
