package com.zya.blog.zyablog.UI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zya.blog.zyablog.dao.UserDao;

@Controller
@RequestMapping("test")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UserTestResource {
	
	private static final Logger logger = Logger.getLogger(UserTestResource.class);
	
	@Autowired
	private UserDao dao;

	@RequestMapping(value = "tt",method = RequestMethod.GET)
	@RequiresRoles(value = { "zya" })
	public void getUserList(){
		System.out.println("2");
		dao.getUserByAcount("zya");
	}
	
}
