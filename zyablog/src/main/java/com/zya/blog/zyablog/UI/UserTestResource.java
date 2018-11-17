package com.zya.blog.zyablog.UI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.zya.blog.zyablog.dao.UserDao;
import com.zya.blog.zyablog.entity.User;

@Controller
@Path(value = "zyablog")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class UserTestResource {
	
	@Autowired
	private UserDao dao;

	@GET
	@Path(value = "getUserList")
	public User getUserList(){
		return dao.getList();
	}
	
}
