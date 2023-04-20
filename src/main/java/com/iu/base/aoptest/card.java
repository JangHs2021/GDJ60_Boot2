package com.iu.base.aoptest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class card {
	
	@Before("execution(* com.iu.base.aoptest.Transport.use*())")
	public void cardCheck() {
		log.error("카드 체크");
	}
}
