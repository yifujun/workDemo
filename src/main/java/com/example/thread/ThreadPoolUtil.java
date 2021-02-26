package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 * 线程池管理工具类.
 *
 * @version V1.0
 * @classname: com.metlife.dgt.graphic.modules.util.ThreadPoolUtil.java
 * @copyright Powered By wechat
 * @author: somnus
 * @date: 2019-11-16 21:23:31
 */
public class ThreadPoolUtil {

    /** 根据cpu的数量动态的配置核心线程数和最大线程数. */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /** 核心线程数 = CPU核心数 + 1. */
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;

    /** 核心线程数 = CPU核心数 + 1. */
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    /** 非核心线程闲置时超时1s. */
    private static final int KEEP_ALIVE = 1;

    /** ThreadPoolExecutor. */
    public static volatile ThreadPoolExecutor threadPool;


    /**
     * 无返回值直接执行.
     * @param runnable 无返回值运行
     */
    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    /**
     * 返回值直接执行.
     * @param callable 返回值运行
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getThreadPool().submit(callable);
    }

    /**
     * 单例获取线程池.
     * @return 线程池对象
     */
    private static ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            synchronized (ThreadPoolUtil.class) {
                if (threadPool == null) {
                    threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(32), new ThreadPoolExecutor.CallerRunsPolicy());
                }
                return threadPool;
            }
        }
        return threadPool;
    }
}
