package com.wei.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspect {
    @Pointcut("execution(* com.wei.spring.aop.biz.user..*.*(..))") // 切点表达式
    private void anyBiz() {
    } // 切点签名方法

    /**
     * 环绕通知。通过ProceedingJoinPoint参数除了JoinPoint的功能外，还可以控制方法是否执行。
     */
    @Around("anyBiz()")
    public Object aroundAnyBiz(ProceedingJoinPoint point) throws Throwable {
        System.out.println("anyBiz around advice start " + point.getArgs());
        Object result = null;
        try {
            // 该方法有重载，可以修改传入参数
            result = point.proceed();
        } finally {
            System.out.println("anyBiz around advice end" + result);
        }
        return result;
    }

    @Pointcut("within(com.wei.spring.aop.service.user..*)") // 切点表达式
    private void anyService() {
    } // 切点签名方法

    /**
     * 环绕通知。通过ProceedingJoinPoint参数除了JoinPoint的功能外，还可以控制方法是否执行。
     */
    @Around("anyService()")
    public Object aroundAnyService(ProceedingJoinPoint point) throws Throwable {
        System.out.println("anyService around advice start " + point.getArgs());
        Object result = null;
        try {
            // 该方法有重载，可以修改传入参数
            result = point.proceed();
        } finally {
            System.out.println("anyService around advice end" + result);
        }
        return result;
    }


    @Pointcut("within(com.wei.spring.aop.rpc..*)") // 切点表达式
    private void anyRpc() {
    } // 切点签名方法

    /**
     * 环绕通知。通过ProceedingJoinPoint参数除了JoinPoint的功能外，还可以控制方法是否执行。
     */
    @Around("anyRpc()")
    public Object aroundAnyRpc(ProceedingJoinPoint point) throws Throwable {
        System.out.println("anyRpc around advice start " + point.getArgs());
        Object result = null;
        try {
            // 该方法有重载，可以修改传入参数
            result = point.proceed();
        } finally {
            System.out.println("anyRpc around advice end" + result);
        }
        return result;
    }

}
