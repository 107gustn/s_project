package com.care.root.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component //빈으로 등록
@Aspect //AOP로 등록
@EnableAspectJAutoProxy //Proxy(프록시): 중간단계 역할 - 특정메소드 호출되기전에 중간에서 처리
public class AOPConfig {
	
	@Around("execution(* com.care.root.board.service.BoardServiceImpl.boardAllList(..))") //특정메소드 실행되기 전과 실행된 후, 총 두번 실행된다.(실행하고자하는 특정 메소드 지정(..:모든 매개변수를 받아줌))
	public void commonAop(ProceedingJoinPoint jp) {
		System.out.println("메소드 실행전....");
		try {
			jp.proceed(); //현재 메소드 일시 중지 후 사용자 지정 메소드 실행
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("메소드 실행 후...");
	}
	
	//@After //특정 메소드 실행 후 동작
	@Before("execution(* com.care.root.board.controller.BoardController.writeForm(..))") //지정메소드 실행 전 동작
	public void test() {
		System.out.println("test......");
	}
	
	Logger LOG = Logger.getGlobal();
	@Around("execution(* com.care.root.board.controller.*.*(..))") //모든 메소드에 대해서 호출
	public Object aop02(ProceedingJoinPoint jp) {
		Object[] params = jp.getArgs(); //사용자 요청시 파라미터를 가져옴
		for( Object o : params) {
			LOG.log(Level.INFO,"들어온 파라미터 값 : " + o);
		}
		Object obj = null;
		
		try {
			obj = jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		LOG.log(Level.INFO, "실행된 메소드 : " + obj); //log = System.out.print
		return obj;
		
	}
	
	
}
