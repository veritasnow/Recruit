package com.recruit.config;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalNonceAdvice {

	@ModelAttribute
	public void addCspNonce(HttpServletRequest request) {
		// nonce 생성
		String nonce = UUID.randomUUID().toString().replace("-", "");
		
		// HTML에서 ${cspNonce}로 사용 가능
		request.setAttribute("cspNonce", nonce);
	}
}
