package com.nbst.overall;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nbst.model.Db;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author zm
 *
 */
@Aspect
@Slf4j
//@Component
public class DataSourceAspect {
    
	@Around("execution(* com.nbst.dao.mapper.iPDSwebs.*.*(..))")
	public Object beforeMapper(ProceedingJoinPoint joinPoint) throws Throwable{
		Signature sig = joinPoint.getSignature();
		String method = sig.getName();
		log.debug("方法名："+sig.getName());
//		log.debug(session.getAttribute("urls"));
		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    	HttpSession session=attr.getRequest().getSession(true);
    	Db db = (Db)session.getAttribute("db");
    	log.debug(db.getSjkUrl());
//		log.debug(joinPoint.getTarget().getClass().getDeclaredAnnotation(PrivateDatasource.class));
		return joinPoint.proceed(joinPoint.getArgs());
	}
	
}
