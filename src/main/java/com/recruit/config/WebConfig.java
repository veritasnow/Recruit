package com.recruit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**").addResourceLocations("file:///C:/upload/");
//		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		log.info("webConfig - addInterceptors");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
