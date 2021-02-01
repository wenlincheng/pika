package com.wenlincheng.pika.message.dto;

import com.wenlincheng.pika.message.model.EmailFile;
import lombok.Data;

import java.util.List;

/**
 * 邮件
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:24 下午
 */
@Data
public class EmailPoster {

    /**
     * 主题
     */
    private String title;

    /**
     * 内容
     */
    private String body;

    /**
     * 发送对象，多个逗号分隔
     */
    private String sendTo;

    /**
     * 抄送对象，多个逗号分隔
     */
    private String copyTo;

    /**
     * 附件
     */
    private List<EmailFile> files;
}
