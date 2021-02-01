package com.wenlincheng.pika.message.sender.email;

import com.sun.mail.util.MailSSLSocketFactory;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.message.enums.EmailSenderSecurityTypeEnum;
import com.wenlincheng.pika.message.model.EmailFile;
import com.wenlincheng.pika.message.dto.EmailPoster;
import com.wenlincheng.pika.message.model.EmailSenderSource;
import com.wenlincheng.pika.message.model.EmailTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * 默认邮件发送者
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/24 3:32 下午
 */
@Slf4j
@Component
public class DefaultEmailSender implements EmailSender {

    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";

    /**
     * 邮箱服务器
     */
    private EmailSenderSource emailSenderSource;

    public DefaultEmailSender(EmailSenderSource emailSenderSource) {
        this.emailSenderSource = emailSenderSource;
    }

    @Override
    public Boolean send(EmailPoster emailPoster) {
        Session session = getSession();
        try {
            MimeMessage message = new MimeMessage(session);
            // 发送人
            message.setFrom(new InternetAddress(emailSenderSource.getSmtpUser()));
            // 收件人
            if (StringUtils.isNotBlank(emailPoster.getSendTo())) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPoster.getSendTo()));
            }
            // 抄送人
            if (StringUtils.isNotBlank(emailPoster.getCopyTo())) {
                message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailPoster.getCopyTo()));
            }
            // 标题
            message.setSubject(emailPoster.getTitle());
            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本
            messageBodyPart.setContent(emailPoster.getBody(), "text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);
            // 附件
            List<EmailFile> files = emailPoster.getFiles();
            if (CollectionUtils.isNotEmpty(files)) {
                for (EmailFile file : files) {
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(file.getUrl());
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(file.getName());
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            message.setContent(multipart);
            // 发送
            Transport.send(message);
            log.debug("发送邮件成功");
        } catch (MessagingException e) {
            throw new BaseException();
        }
        return true;
    }

    @Override
    public Boolean sendTemplate(EmailTemplate emailTemplate, Map<String, Object> objectData, String sendTo, String copyTo) {
        EmailPoster emailPoster = new EmailPoster();
        emailPoster.setSendTo(sendTo);
        emailPoster.setCopyTo(copyTo);
        emailPoster.setTitle(format(objectData, emailTemplate.getTile()));
        emailPoster.setBody(format(objectData, emailTemplate.getBody()));

        return send(emailPoster);
    }

    /**
     * 替换模板内容参数
     *
     * @param objectData 参数
     * @param templateStr 模板内容
     * @return java.lang.String
     */
    private static String format(Map<String, Object> objectData, String templateStr) {
        StringBuilder buf = new StringBuilder(templateStr);
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        while (startIndex != -1) {
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != 1) {
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex;
                Object val = objectData.get(placeholder);
                if (Objects.nonNull(val)) {
                    String valStr = String.valueOf(val);
                    buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), valStr);
                    nextIndex = startIndex + valStr.length();
                } else {
                    throw new BaseException();
                }
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }

    /**
     * 获取Session
     *
     * @return
     */
    private Session getSession() {
        try {
            // 发件人电子邮箱
            String from = emailSenderSource.getSmtpUser();
            // 邮件服务器host
            String host = emailSenderSource.getSmtpHost();
            // 邮件服务器端口
            Integer port = emailSenderSource.getSmtpPort();
            // 加密方式
            String smtpSecurity = emailSenderSource.getSecurityType();
            // 获取系统属性
            Properties properties = System.getProperties();
            // 设置邮件服务器
            properties.setProperty("mail.smtp.host", host);
            if (!ObjectUtils.isEmpty(port)) {
                properties.setProperty("mail.smtp.port", port.toString());
            }
            properties.put("mail.smtp.auth", "true");
            if (EmailSenderSecurityTypeEnum.SSL.getValue().equals(smtpSecurity)) {
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.ssl.socketFactory", sf);
            }
            if (EmailSenderSecurityTypeEnum.STARTTLS.getValue().equals(smtpSecurity)) {
                properties.put("mail.smtp.starttls.enable", "true");
            }
            // 获取默认的session对象
            return Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, emailSenderSource.getSmtpPassword());
                }
            });
        } catch (Exception e) {
            throw new BaseException();
        }
     }

    public static void main(String[] args) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 123456);
        hashMap.put("time", 5);
        String s = format(hashMap, "您的验证码是：${code}, 请在${time}分钟内输入");
        System.out.println(s);
    }
}
