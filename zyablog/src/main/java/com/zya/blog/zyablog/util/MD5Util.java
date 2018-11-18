package com.zya.blog.zyablog.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * MD5加密工具类
 * 
 * @author an
 *
 */
public class MD5Util {
	public static String getMD5(String password) {
		String hashAlgorithmName = "MD5";
		// 参数salt为null表示不加盐，此处与realm的实现类SimpleAuthenticationInfo方法保持一致
		SimpleHash obj = new SimpleHash(hashAlgorithmName, password, null);
		return obj.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getMD5("wly"));
	}
}
