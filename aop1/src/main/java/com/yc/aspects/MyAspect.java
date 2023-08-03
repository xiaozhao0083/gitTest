package com.yc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class MyAspect {
    @Pointcut("execution(* com.yc.biz.*.make*(..))")
    private void a() {
    }

    @Before("a()")
    public void recordTime() {
        Date date = new Date();
        System.out.println("=====下单时间：" + date);
    }

    @Pointcut("execution(int com.yc.biz.*.findPid(String))")
    private void c() { }

    private Map<String,Long> map=new ConcurrentHashMap<>();

    @AfterReturning(pointcut = "c()",returning = "retValue")
    public void recordPnameCount(JoinPoint jp,int retValue){
        Object[] args = jp.getArgs();
        String pname= (String) args[0];
        Long num=1L;
        if(map.containsKey(pname)){
            num=map.get(pname);
            num++;
        }
        map.put(pname+":"+retValue, num);
        System.out.println("统计结果："+map);
    }


}
