package com.wenlincheng.pika.message;

import com.wenlincheng.pika.message.entity.dto.EmailPoster;
import com.wenlincheng.pika.message.sender.email.DefaultEmailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PikaMessageApplicationTests {

    @Autowired
    private DefaultEmailSender emailSender;

    @Test
    void contextLoads() {

    }


//    @Test
//    void sendEmail() {
//        EmailPoster emailPoster = new EmailPoster();
//        emailPoster.setTitle("测试邮件")
//                .setSendTo("1511181420@qq.com")
//                .setBody("这是一封测试邮件");
//        emailSender.send(emailPoster);
//    }

}
