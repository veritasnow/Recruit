package com.recruit.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LoggerAspect {
	
	@Around("execution(* com.recruit..controller.*Controller.*(..)) or execution(* com.recruit..service.*Service.*(..)) or execution(* com.recruit..mapper.*Mapper.*(..))")
	public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

		String type = "";
		String name = joinPoint.getSignature().getDeclaringTypeName();

		if (name.contains("Controller") == true) {
			type = "Controller ===> ";

		} else if (name.contains("Service") == true) {
			type = "Service ===> ";

		} else if (name.contains("Mapper") == true) {
			type = "Mapper ===> ";
		}
    	log.debug("LoggerAspect : " + type + name + "." + joinPoint.getSignature().getName() + "()");

		return joinPoint.proceed();
	}
	
	
}