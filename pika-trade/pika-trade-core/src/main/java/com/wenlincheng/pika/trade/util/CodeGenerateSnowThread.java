package com.wenlincheng.pika.trade.util;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class CodeGenerateSnowThread implements Runnable{

    private static final SnowFlake SNOW_FLAKE=new SnowFlake(2,3);


    public CodeGenerateSnowThread() {
    }

    @Override
    public void run() {
        // 采用雪花算法生成订单编号s
        System.out.println(SNOW_FLAKE.nextId());
    }
}