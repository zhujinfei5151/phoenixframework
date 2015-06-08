package org.phoenix.web.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("logAspect")
public class LogAspect {
    public void logStart(JoinPoint jp){
    	
    	PhoenixLogger.info("request:Class:"+jp.getTarget().getClass().getName()+", method:"+jp.getSignature().getName()+" , parameter:"+Arrays.toString(jp.getArgs()));
		PhoenixLogger.info("调用前加入日志");
    }
	
    public void logEnd(JoinPoint jp){
    	PhoenixLogger.info("结束后加入日志");
    }
	
    public void logAround(ProceedingJoinPoint pj) throws Throwable{
    	PhoenixLogger.info("方法调用之前加入日志");
    	pj.proceed();//执行方法
    	PhoenixLogger.info("方法调用之后加入日志");
    }
	
}
