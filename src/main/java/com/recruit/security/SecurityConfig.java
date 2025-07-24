package com.recruit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.recruit.security.filter.CspHeaderFilter;
import com.recruit.security.filter.SqlInjectionFilter;
import com.recruit.security.filter.XssFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
     * CSP 헤더 Filter Field
     */
	@Autowired
	private CspHeaderFilter cspHeaderFilter;
	
	/**
     * XSS Filter Field
     */
	@Autowired
	private XssFilter xssFilter;
	
	/**
     * CSP 헤더 필터 Filter Field
     */
	@Autowired
	private SqlInjectionFilter sqlInjectionFilter;
	
	// 스프링 시큐리티 로그인 기능 구현시 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("------------------------Secrurity Configure Start---------------------------");

		http
			.addFilterBefore(cspHeaderFilter, BasicAuthenticationFilter.class)
			.addFilterBefore(xssFilter, BasicAuthenticationFilter.class)
			.addFilterBefore(sqlInjectionFilter, BasicAuthenticationFilter.class)
			.authorizeRequests(auth -> auth.anyRequest().permitAll());

		http.headers().frameOptions().sameOrigin(); // iframe을 사용하지 않을 경우 제외할것
		
		/*
		 * 로그인 비활성화
		 * 
		http.formLogin()
		.loginPage("/login") // 로그인 페이지 -> customPage 셋팅
		.loginProcessingUrl("/doLogin") // 로그인버튼을 클릭하면 catch한다
		.usernameParameter("id")
		.passwordParameter("password")
		.defaultSuccessUrl("/main", true)
		.failureForwardUrl("/loginFailed");
		
		
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.invalidateHttpSession(true).deleteCookies("JSESSIONID")
		.logoutSuccessUrl("/main");		
		
		
		http.headers().frameOptions().disable();
		http.headers().frameOptions().sameOrigin();
		http.logout();
		*/
		
		
		/*
		 * 접근권한 비활성화
		 * 
		http.authorizeRequests()
		.antMatchers("/user/login").permitAll()
		.antMatchers("/user/join").permitAll()
		.antMatchers("/admin/*").hasRole("ADMIN");
		 */

		log.info("------------------------Secrurity Configure End---------------------------");
	}
	
}