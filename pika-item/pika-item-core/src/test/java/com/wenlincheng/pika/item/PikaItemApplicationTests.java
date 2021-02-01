package com.wenlincheng.pika.item;

import com.wenlincheng.pika.common.data.generator.CodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PikaItemApplicationTests {

    @Test
    void contextLoads() {
        new CodeGenerator().gen();
    }

}
