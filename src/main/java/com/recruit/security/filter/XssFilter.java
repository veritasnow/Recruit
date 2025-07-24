package com.recruit.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.recruit.security.xss.XssRequestWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class XssFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (log.isDebugEnabled()) log.debug("XssFilter doFilterInternal");
		
		chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
	}
}
