package com.zya.blog.zyablog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zya.blog.zyablog.entity.User;

/**
 * 用户dao
 * 
 * @author an
 *
 */
@Mapper
public interface UserDao {

	/**
	 * 根据账户查找用户
	 * 
	 * @param acount
	 * @return
	 */
	User getUserByAcount(@Param("acount") String acount);
}