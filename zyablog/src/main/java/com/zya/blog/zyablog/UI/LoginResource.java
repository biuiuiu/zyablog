package com.zya.blog.zyablog.UI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;

import com.zya.blog.zyablog.entity.LoginVO;
import com.zya.blog.zyablog.response.ResponseVO;

@Controller
@Path("zyablog")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class LoginResource {

	private static Logger logger = Logger.getLogger(LoginResource.class);
	
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
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("验证失败");
			return new ResponseVO("2", "验证失败", null);
		}
		if (subject.isAuthenticated()) {
			return new ResponseVO("1", "success", null);
		}else {
			return new ResponseVO("2", "验证失败", null);
		}
	}
}
