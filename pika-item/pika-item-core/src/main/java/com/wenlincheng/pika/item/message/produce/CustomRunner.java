package com.wenlincheng.pika.item.message.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class CustomRunner implements CommandLineRunner {
    private final String bindingName;

    public CustomRunner(String bindingName) {
        this.bindingName = bindingName;
    }

    @Autowired
    private SenderService senderService;

    @Autowired
    private MySource mySource;

    @Override
    public void run(String... args) throws Exception {
        if ("output1".equals(this.bindingName)) {
            int count = 5;
            for (int index = 1; index <= count; index++) {
                String msgContent = "msg-" + index;
                if (index % 3 == 0) {
                    senderService.send(msgContent);
                }
                else if (index % 3 == 1) {
                    senderService.sendWithTags(msgContent, "tagStr");
                }
                else {
                    senderService.sendObject(new Foo(index, "foo"), "tagObj");
                }
            }
        }
        else if ("output3".equals(this.bindingName)) {
            int count = 20;
            for (int index = 1; index <= count; index++) {
                String msgContent = "pullMsg-" + index;
                mySource.output3()
                        .send(MessageBuilder.withPayload(msgContent).build());
            }
        }

    }
}
