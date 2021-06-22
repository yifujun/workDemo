package com.example.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 接口日志面向切面AOP.
 *
 * @author 易富军
 * @Aspect 声明这是一个面向切面类之后可以使用前置通知，后置.....
 * @Component 把这个类注入spring容器中，使用完这个注解之后，这个类就不属于各种归类(@controller、@server)
 * @Order(99)  这个注解使用来控制配置类的加载顺序  （括号里面的数字越小，加载顺序越快）
 */
@Aspect
@Order(99)
@Component
public class LogAspect {

    /**
     * 线程内部的存储类.
     * 只有指定现成能获取里面的数据
     */
    private static ThreadLocal<Long> STARTTIME = new ThreadLocal<>();
    /**
     * 线程url地址.
     */
    private static ThreadLocal<String> URL = new ThreadLocal<>();
    /**
     *  json解析工具.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 切入点表达式.
     */
    @Pointcut(value = "includePointcut()")
    public void controllerPointcut() {
        
    }
    
    @Pointcut(value = "execution(* com.example..*Controller.*(..))"
            + "|| execution(* com.example.controller..*(..))")
    public void includePointcut() {
    }

    /**
     * 前置通知.
     */
    @Before(value = "controllerPointcut()")
    public void doBeforeRequest() {
         System.out.println();
    }
    
    
}
