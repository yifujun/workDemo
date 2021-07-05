package com.example.aspect;

import com.example.util.SystemLogUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.ClassPool;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口日志面向切面AOP.
 * 
 * 切面（Aspect）：一个关注点的模块化，这个关注点实现可能另外横切多个对象。比如说事务管理就是J2EE应用中一个很好的横切关注点例子。切面用Spring的Advisor或拦截器实现。
 * 连接点：（Joinpoint）：程序执行过程中明确的点; 如方法的调用或特定的异常被抛出。
 * 通知（Advice）：在特定的连接点，AOP框架执行的动作。各种类型的通知包括“around”、“before”和“throws”通知; 通知类型将在下面讨论;
 *              许多AOP框架包括Spring都是以拦截器做通知模型，维护一个“围绕”连接点的拦截器链。
 * 切入点（Pointcut）：指定一个通知将被引发的一系列连接点的集合。AOP框架必须允许开发者指定切入点，例如，使用正则表达式。
 * 目标对象（Target Object）：包含连接点的对象，也被称作被通知或被代理对象。
 * AOP代理（AOP Proxy）：AOP框架创建的对象，包含通知。在Spring中，AOP代理可以是JDK动态代理或CGLIB代理。(同包下面有操作实例 - demoaop)
 * 编制(Weaving)：组装方面来创建一个被通知对象。这可以在编译时完成（例如使用AspectJ编译器），也可以在运行时完成。Spring和其他纯Java AOP框架一样，在运行完成织入
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

    /** 线程内部的存储类; 只有指定现成能获取里面的数据. */
    private static ThreadLocal<Long> startTime = new ThreadLocal<>();
    /** 线程url地址. */
    private static ThreadLocal<String> url = new ThreadLocal<>();
    /** json解析工具. */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /** 切入点表达式. */
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
    public void doBeforeRequest(JoinPoint joinPoint) {
        SystemLogUtils.info("====LogAspect, 开始请求====");
        //设置请求时间
        startTime.set(System.currentTimeMillis());
        
        // 获取HttpServletRequest
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // 获取当前接口的请求路径
        String requestUrl = request.getRequestURI();
        //获取目标对象的方法对象
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        
        //获取请求方法的类型
        String requestmethod = methodSignature.getMethod().getName();
        String method = request.getMethod();
        
        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz;
        String clazzName;
        StringBuffer parms = new StringBuffer();
        try {
            clazz = Class.forName(classType);
            clazzName = clazz.getName();
        } catch (ClassNotFoundException e) {

        } /*catch (NotFoundException e) {

        } */catch (Exception e) {
            
        }
    }

    /**
     * 获取接口请求参数.
     * 
     * @param cls 接口类
     * @param clazzName 类名
     * @param methodName 目标方法名 
     * @param args 请求参数
     * @return StringBuffer
     * @throws NotFoundException
     */
    private StringBuffer getNameAndArgs(Class<?> cls, String clazzName, String methodName, 
                                        Object[] args) throws NotFoundException {
        // 封装map接收请求参数
        Map<String, Object> nameAndArgs = new HashMap<>(16);
        // 缓存CTClass对象的容器
        ClassPool pool = ClassPool.getDefault();
        
        
        return  null;
    }
    
    
}
