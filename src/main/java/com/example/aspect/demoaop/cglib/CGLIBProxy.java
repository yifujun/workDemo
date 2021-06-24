package com.example.aspect.demoaop.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于CGLIB实现.
 * 
 * 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP；但是也可以强制使用CGLIB实现AOP
 * 如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
 * 注：JDK动态代理要比cglib代理执行速度快，但性能不如cglib好。所以在选择用哪种代理还是要看具体情况，一般单例模式用cglib比较好
 * @author 易富军
 */
public class CGLIBProxy implements MethodInterceptor {

    //被代理的目标对象
    private Object targetObject;

    /**
     * 构造代理对象
     *
     * @param targetObject 传递的真实对象
     * @return 代理对象
     */
    public Object createProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass()); // 设置代理目标 
        enhancer.setCallback(this);  // 设置回调 
        return enhancer.create();

    }

    /**
     * 在代理实例上处理方法调用并返回结果.
     *
     * @param object      代理类
     * @param method      被代理的方法
     * @param objects     该方法的参数数组
     * @param methodProxy 方法代理
     * @return
     * @throws Throwable  Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置处理开始 ...");
        Object result = methodProxy.invoke(targetObject, objects);
        System.out.println("后置处理开始  ...");
        System.out.println(" 调用结束 ...");
        return result;
    }
}
