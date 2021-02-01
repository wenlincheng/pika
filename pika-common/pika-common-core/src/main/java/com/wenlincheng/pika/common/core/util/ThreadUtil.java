package com.wenlincheng.pika.common.core.util;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程相关的util
 * @author zhulm
 *
 */
@Slf4j
public class ThreadUtil {
	
	/**
	 * 默认的线程池
	 */
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(100);
	
	/**
	 * 睡眠固定时间
	 * @param miliseconds
	 */
	public static final void sleepNoException(long miliseconds){
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			log.error("InterruptedException, ex="+e.getMessage(), e);
		}
	}
	
	/**
	 * 执行任务
	 * @param t
	 */
	public static final void execute(Runnable t)
	{
		if(t != null){
			threadPool.execute(t);
		}
	}
}
