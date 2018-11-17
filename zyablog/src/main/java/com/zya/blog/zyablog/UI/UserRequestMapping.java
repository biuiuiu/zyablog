package com.zya.blog.zyablog.UI;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zya.blog.zyablog.dao.UserDao;
import com.zya.blog.zyablog.entity.User;

@Controller
@RequestMapping(value = "test")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class UserRequestMapping {
	
	@Autowired
	private UserDao dao;
	
	@RequestMapping(value = "tt",method = RequestMethod.GET)
	@ResponseBody
	public User name() {
		System.out.println("a");
		return dao.getList();
	}
}
