package com.zya.blog.zyablog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zya.blog.zyablog.entity.Role;

/**
 * 角色dao
 * 
 * @author an
 *
 */
@Mapper
public interface RoleDao {

	Role getRoleById(@Param("id") long id);
}