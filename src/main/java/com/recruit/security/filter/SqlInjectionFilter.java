package com.recruit.security.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SqlInjectionFilter implements Filter {

	// SQL 인젝션 패턴 목록
    private static final Pattern[] SQL_PATTERNS = {
        Pattern.compile("('.+--)|(--)|(%7C)", Pattern.CASE_INSENSITIVE),
        Pattern.compile("\\b(select|insert|update|delete|drop|truncate|exec|union|or|and)\\b", Pattern.CASE_INSENSITIVE)
    };
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	if (log.isDebugEnabled()) log.debug("SqlInjectionFilter doFilterInternal");
    	
        HttpServletRequest req = (HttpServletRequest) request;
        Enumeration<String> paramNames = req.getParameterNames();

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String[] values = req.getParameterValues(paramName);

            for (String value : values) {
                for (Pattern pattern : SQL_PATTERNS) {
                    if (pattern.matcher(value).find()) {
                        throw new ServletException("SQL Injection 시도 탐지됨: " + value);
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }
}
