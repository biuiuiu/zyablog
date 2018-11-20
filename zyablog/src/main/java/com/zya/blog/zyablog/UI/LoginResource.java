package com.zya.blog.zyablog.UI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import com.zya.blog.zyablog.entity.LoginVO;
import com.zya.blog.zyablog.entity.User;
import com.zya.blog.zyablog.response.ResponseVO;
import com.zya.blog.zyablog.service.UserService;

@Controller
@Path("zyablog")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class LoginResource {

	private static Logger logger = Logger.getLogger(LoginResource.class);
	
	
	@Autowired
	private UserService service;
	
	@Autowired
	private Environment env;
	
	@POST
	@Path(value = "/login")
	public ResponseVO login(LoginVO vo){
		if (vo == null) {
			return new ResponseVO("2", "输入用户或密码", null);
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(vo.getUserName(), vo.getPassword());
		//进行密码验证
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
            // 捕获密码错误异常
			ice.printStackTrace();
			logger.error("密码错误");
			String result = "密码错误";
			int limit = Integer.valueOf(env.getProperty("retryLimit"));
			String msg = ice.getMessage();
			int temp = 0;
			if (StringUtils.isNumeric(msg)) {
				if ((temp = (limit - Integer.valueOf(msg)))>0) {
					result = "密码错误，剩余登录次数为"+temp +"次";
				}else {
					result = "登录次数过多,请明天再试";
				}
			}
			return new ResponseVO("2", result, null);
        } catch (UnknownAccountException uae) {
            // 捕获未知用户名异常
        	uae.printStackTrace();
			logger.error("未知用户");
			return new ResponseVO("2", "未知用户", null);
        } catch (ExcessiveAttemptsException eae) {
            // 捕获错误登录过多的异常
        	eae.printStackTrace();
			logger.error("登录次数过多");
			return new ResponseVO("2", "登录次数过多,请明天再试", null);
        }catch (Exception e) {
			e.printStackTrace();
			logger.error("验证失败");
			return new ResponseVO("2", "验证失败", null);
		}
		if (subject.isAuthenticated()) {
			User user = service.getUserByAcount(vo.getUserName());
			subject.getSession().setAttribute("user", user);
			return new ResponseVO("1", "success", null);
		}else {
			return new ResponseVO("2", "验证失败", null);
		}
	}
	
	@GET
	@Path(value = "logout")
	public void logout(){
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getSession().getAttribute("user");
		System.out.println(user.getAcount());
		subject.logout();
		subject.getSession().stop();
	}
	
	@GET
	@Path(value = "gets")
	public User getUser(){
		System.out.println("2");
		return new User();
	}
}
