package com.wei.spring.app.testcore;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class TestAspectJ {
    @Pointcut("execution(* *.test(..))")
    public void test() {

    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("afterTest");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("aroundTest start");
        Object ret = null;
        try {
            ret = p.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("aroundTest end");
        return ret;
    }

}
