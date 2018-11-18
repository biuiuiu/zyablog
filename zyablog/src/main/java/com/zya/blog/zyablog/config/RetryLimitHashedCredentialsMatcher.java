package com.zya.blog.zyablog.config;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 自定义验证方式，限制重试次数
 * 
 * @author an
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private static Logger logger = Logger.getLogger(RetryLimitHashedCredentialsMatcher.class);

	public RetryLimitHashedCredentialsMatcher() {
	}

	/**
	 * 调用父类认证方式时需要传入加密算法
	 * 
	 * @param hashAlgorithm
	 */
	public RetryLimitHashedCredentialsMatcher(String hashAlgorithm) {
		super(hashAlgorithm);
	}

	@Autowired
	private Environment env;

	private ConcurrentHashMap<String, Integer> passwordRetryCache = new ConcurrentHashMap<>();

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String userName = token.getPrincipal().toString();
		Integer count = passwordRetryCache.get(userName);
		if (null == count) {
			passwordRetryCache.put(userName, 0);
			count = 0;
		}
		int retryLimit = Integer.valueOf(env.getProperty("retryLimit"));
		if (count > retryLimit) {
			throw new ExcessiveAttemptsException();
		}
		boolean match = super.doCredentialsMatch(token, info);
		if (match) {
			passwordRetryCache.remove(userName);
		} else {
			count++;
			passwordRetryCache.put(userName, count);
			throw new IncorrectCredentialsException(count.toString());
		}
		return match;
	}

	/**
	 * 设置定时任务清楚缓存的记录
	 */
	@Scheduled(cron = "${cron}")
	public void clearRetryLimit() {
		logger.info("定时清楚用户登录次数缓存");
		passwordRetryCache.clear();
	}
}
