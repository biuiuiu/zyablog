package com.zya.blog.zyablog.myshiro;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashSet;

import javax.print.attribute.standard.RequestingUserName;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zya.blog.zyablog.entity.Role;
import com.zya.blog.zyablog.entity.User;
import com.zya.blog.zyablog.service.RoleService;
import com.zya.blog.zyablog.service.UserService;
import com.zya.blog.zyablog.util.RoleEnum;

public class MyshiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService service;

	@Autowired
	private RoleService roleService;

	@Value("${guest}")
	private String guestPerm;

	@Value("${admin}")
	private String adminPerm;

	/**
	 * 获取用户对于的角色以及角色拥有的权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 用户账户
		String userName = (String) principalCollection.getPrimaryPrincipal();
		User user = service.getUserByAcount(userName);
		Long roleId = user.getRoleId();
		Role role = roleService.getRoleById(roleId);
		// 认证对象
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 添加角色列表
		info.addRole(role.getRoleName());
		if (RoleEnum.guest.equals(role.getRoleName())) {
			info.addStringPermissions(new HashSet<>(Arrays.asList(guestPerm.split(","))));
		} else if (RoleEnum.admin.equals(role.getRoleName())) {
			info.addStringPermissions(new HashSet<>(Arrays.asList(adminPerm.split(","))));
		}
		return info;
	}

	/**
	 * 用户认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token.getPrincipal() == null) {
			return null;
		}
		//获取用户信息
		String userName = token.getPrincipal().toString();
		User user = service.getUserByAcount(userName);
		if (null == user) {
			//shiro会返回异常
			return null;
		}else {
			//设置MD5加密，盐值为null
			//构造函数第二个参数是数据库的密码
			SimpleAuthenticationInfo info = 
					new SimpleAuthenticationInfo(userName, user.getPassword(), null, this.getClass().getName());
			return info;
		}
	}
	
	

}
