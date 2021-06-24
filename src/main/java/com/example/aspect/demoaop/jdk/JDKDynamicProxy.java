package com.example.aspect.demoaop.jdk;

import org.springframework.cglib.proxy.Proxy;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JDK自动代理实现.
 * 
 * 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP；但是也可以强制使用CGLIB实现AOP
 * 如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
 * 注：JDK动态代理要比cglib代理执行速度快，但性能不如cglib好。所以在选择用哪种代理还是要看具体情况，一般单例模式用cglib比较好
 *
 * @author 易富军
 */
public class JDKDynamicProxy implements InvocationHandler {

    /**
     * 被代理的目标类.
     */
    private Object proxyObj;

    /**
     * Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler invocationHandler)
     * loader: 类加载器，一个ClassLoader对象，定义了由哪个ClassLoader对象来对生成的代理对象进行代理
     * interfaces: 一个interfaces对象的数组，表示的是我将要给我需要代理的对象提供一组什么样的接口，如果我提供了一组接口给它,
     * 那么这个代理对象就宣称实现了该接口(多态)，这样我就能调用这组中的接口的方法了。
     * invocationHandler: 表示的是当我这个动态代理对象在调用方法的时候，会关联到哪一个invocationHandler对象上
     *
     * @param proxyObj 目标类
     * @return
     */
    public Object newProxy(Object proxyObj) {
        this.proxyObj = proxyObj;
        return Proxy.newProxyInstance(proxyObj.getClass().getClassLoader(), proxyObj.getClass().getInterfaces(), this);
    }

    /**
     * 执行目标对象.
     *
     * @param proxy  被代理的对象
     * @param method 需要调用的方法
     * @param args   方法调用时所用的参数
     * @return Object
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            before();
            Object object = method.invoke(this.proxyObj, args);
            after();
            return object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 前置通知.
     */
    public void before() {
        System.out.println("开始执行目标对象之前.......");
    }

    /**
     * 后置通知.
     */
    public void after() {
        System.out.println("开始执行目标对象之后.......");
    }

}
