package com.example.util;

/**
 * 性能测试监控类.
 *
 * @author 易富军
 */
public class PerformanceDemo {
    private static String s = "";

    private static  long n = 10000000;

    //s == null || s.equals("")
    public static void function1() {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (s == null || s.equals(""));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("[s == null || s.equals(\"\")] use time: " + (endTime - startTime) + "ms");
    }

    //s == null || s.length() <= 0
    public static void function2() {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (s == null || s.length() <= 0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("[s == null || s.length() <= 0] use time: " + (endTime - startTime) + "ms");
    }

    //s == null || s.isEmpty()
    public static void function3() {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (s == null || s.isEmpty())//since jdk1.6 
                ;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("[s == null || s.isEmpty()] use time: " + (endTime - startTime) + "ms");
    }

    //s == null || s == ""
    public static void function4() {
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < n; i++) {
            if (s == null || s == "")
                ;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("[s == null || s == \"\"] use time: " + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) {
        function1();
        function2();
        function3();
        function4();
    }
}
