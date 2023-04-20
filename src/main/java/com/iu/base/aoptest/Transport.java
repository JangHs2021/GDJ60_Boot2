package com.iu.base.aoptest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Transport {
	
	public void useSubway() {
		log.error("지하철 이용");
	}
	
	public void useBus() {
		log.error("버스 이용");
	}
	
	public void takeWalk() {
		log.error("걸어가기");
	}
}
