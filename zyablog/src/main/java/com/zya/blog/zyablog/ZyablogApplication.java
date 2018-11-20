package com.zya.blog.zyablog;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zya.blog.zyablog.UI.UserTestResource;

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class ZyablogApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ZyablogApplication.class, args);
		RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
			System.out.println(entry.getKey().getPatternsCondition().toString());
			System.out.println(entry.getValue().getMethod().getName());
		}
		UserTestResource sResource = context.getBean(UserTestResource.class);
		System.out.println(sResource.toString());
	}

	/**
	 * 添加fastjson解析
	 * 
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
}
