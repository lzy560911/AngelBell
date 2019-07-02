package cn.angelbell.oa.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: ActionAspect
 * @Description:控制层切面类
 * @author liziye
 * @date 2019年7月1日
 */
@Aspect
@Component
public class ActionAspect {

    /**
     * 
     * @Title: actionAspect
     * @Description:切入点表达式
     */
    @Pointcut("execution(public * cn.angelbell.oa.controller.*.*(..))")
    public void actionAspect() {
    }

    /**
     * 
     * @Title: beforeMethod
     * @Description:目标方法执行之前调用
     */
    @Before("actionAspect()")
    public void beforeMethod() {
        System.out.println("目标方法执行之前调用。。。");
    }

    /**
     * 
     * @Title: afterMethod
     * @Description:目标方法返回或者抛出异常之后调用
     */
    @After("actionAspect()")
    public void afterMethod() {
        System.out.println("目标方法返回或者抛出异常之后调用。。。");
    }

    /**
     * 
     * @Title: afterReturningMethod
     * @Description:目标方法返回之后调用,抛出异常时不调用
     */
    @AfterReturning("actionAspect()")
    public void afterReturningMethod() {
        System.out.println("目标方法返回之后调用,抛出异常时不调用。。。");
    }

    /**
     * 
     * @Title: afterThrowingMethod
     * @Description:目标方法抛出异常之后调用,正常返回时不调用
     */
    @AfterThrowing("actionAspect()")
    public void afterThrowingMethod() {
        System.out.println("目标方法抛出异常之后调用,正常返回时不调用。。。");
    }

    /**
     * 
     * @Title: aroundMethod
     * @Description: 环绕通知
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("actionAspect()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕在目标方法之前。。。");
        // 访问目标方法名称
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 访问目标方法的参数
        Object[] paramsArr = joinPoint.getArgs();
        // 执行目标方法
        Object returnObj = joinPoint.proceed(paramsArr);
        System.out.println("执行的方法:" + methodName);
        System.out.println("环绕在目标方法之后。。。");
        return returnObj;
    }

}
