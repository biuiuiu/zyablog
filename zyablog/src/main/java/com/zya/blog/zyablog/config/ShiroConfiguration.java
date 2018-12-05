package com.zya.blog.zyablog.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.zya.blog.zyablog.myshiro.MyShiroFilter;
import com.zya.blog.zyablog.myshiro.MyshiroRealm;

@Configuration
public class ShiroConfiguration {

	// 将自己的验证方式加入容器
	@Bean
	public MyshiroRealm myShiroRealm() {
		MyshiroRealm myShiroRealm = new MyshiroRealm();
		myShiroRealm.setCredentialsMatcher(credentialsMatcher());// 添加密码加密以及错误尝试次数设置
		return myShiroRealm;
	}

	/**
	 * 权限管理，把实现的realm类绑定到shiro的securitymanger中
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myShiroRealm());
		return manager;
	}

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("myShiroFilter", new MyShiroFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		Map<String, String> map = new HashMap<String, String>();
		map.put("/zyablog/login", "anon");// 设置不需要验证的url
		 map.put("*.html", "anon");//设置不需要验证的url
		//
		// // 登出
		//// map.put("/logout", "logout");
		// // 对所有用户认证
		map.put("/**", "myShiroFilter");
		//// // 登录
		// shiroFilterFactoryBean.setLoginUrl("/login.html");
		//// // 首页
		//// shiroFilterFactoryBean.setSuccessUrl("/index");
		//// // 错误页面，认证不通过跳转
		//// shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	
	@Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	// 自定义验证密码方法
	@Bean
	public CredentialsMatcher credentialsMatcher() {
		RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher("MD5");
		return matcher;
	}

}
