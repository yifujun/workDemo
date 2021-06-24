package com.example.aspect.demoaop.cglib;

import org.springframework.stereotype.Service;

/**
 * CGLIB动态代理.
 *
 * @author 易富军
 */
@Service("testCGLIBServiceImpl")
public class TestCGLIBServiceImpl {

    public int add() {
        try {
            System.out.println("CGLIB动态代理开始执行add()..........");
        } catch (Exception e) {
            return -1;
        }
        return 200;
    }
}
