package com.recruit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 스프링 시큐리티 로그인 기능 구현시 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("------------------------Secrurity Configure Start---------------------------");

	    http
        .csrf().and()  // CSRF 토큰 활성화 (기본값은 활성화 상태)
        .authorizeRequests()
        .anyRequest().permitAll();  // 모든 요청 인증 없이 허용		
		
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