package com.recruit.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CspHeaderFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (log.isDebugEnabled()) log.debug("CspHeaderFilter doFilterInternal");

		String nonce = (String) request.getAttribute("cspNonce");
		if (nonce != null) {
			response.setHeader("Content-Security-Policy", "script-src 'self' 'nonce-" + nonce + "'");
		}

        filterChain.doFilter(request, response);
	}
}
