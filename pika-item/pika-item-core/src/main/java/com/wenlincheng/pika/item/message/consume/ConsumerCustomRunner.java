package com.wenlincheng.pika.item.message.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/26 11:13 下午
 */
@Component
public class ConsumerCustomRunner implements CommandLineRunner {

    @Autowired
    private MySink mySink;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            mySink.input5().poll(m -> {
                String payload = (String) m.getPayload();
                if(payload.contains("0")){
                    throw new IllegalArgumentException("111111111111111111111111111111111111111111");
                }
                System.out.println("pull msg: " + payload);
            }, new ParameterizedTypeReference<String>() {
            });
            Thread.sleep(5_00);
        }
    }
}
