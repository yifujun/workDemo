package com.example.aspect.demoaop;


import com.example.aspect.demoaop.cglib.CGLIBProxy;
import com.example.aspect.demoaop.cglib.TestCGLIBServiceImpl;

/**
 * AOP测试.
 *
 * @author 易富军
 */
public class AopDemoMain {

    public static void main(String[] args) {
        
        // == JDK动态代理运行
        /* System.out.println("=============不用代理================");
        // 我们要代理的真实对象 
        TextService testService = new TextServiceImpl();
        testService.add();

        System.out.println("=============JDK代理================");
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy();
        TextService testServiceProxy = (TextService) jdkDynamicProxy.newProxy(testService);
        testServiceProxy.add();*/
        
        // CGLIB代理
        TestCGLIBServiceImpl testCGLIB = new TestCGLIBServiceImpl();
        testCGLIB.add();
        System.out.println("===============CGLIB动态代理=======================");
        CGLIBProxy cglibProxy = new CGLIBProxy();
        TestCGLIBServiceImpl testCGLIBService = (TestCGLIBServiceImpl) cglibProxy.createProxyInstance(testCGLIB);
        testCGLIBService.add();
    }
}
