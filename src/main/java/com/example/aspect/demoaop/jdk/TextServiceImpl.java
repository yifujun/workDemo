package com.example.aspect.demoaop.jdk;

import org.springframework.stereotype.Service;

/**
 * 测试实现类.
 *
 * @author 易富军
 */
@Service("testServiceImpl")
public class TextServiceImpl implements TextService {

    @Override
    public int add() {
        try {
            System.out.println("执行add方法");
        } catch (Exception e) {
            return -1;
        }
        return 200;
    }
}
