package com.wenlincheng.pika.message.sender.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wenlincheng.pika.message.config.SmsSenderConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 默认短信内发送者
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/2/2 10:26 下午
 */
@Slf4j
@Component
public class DefaultSmsSender implements SmsSender {

    @Autowired
    private SmsSenderConfig smsSenderConfig;

    private static IAcsClient client;

    @PostConstruct
    public void initClient() {
        DefaultProfile profile = DefaultProfile.getProfile(smsSenderConfig.getRegionId(), smsSenderConfig.getAccessKey(), smsSenderConfig.getAccessSecret());
        client = new DefaultAcsClient(profile);
    }

    @Override
    public void sendSms(SmsSendParam sendParam) {

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsSenderConfig.getDomain());
        request.setSysVersion(smsSenderConfig.getVersion());
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", sendParam.getPhoneNumbers());
        request.putQueryParameter("SignName", sendParam.getSignName());
        request.putQueryParameter("TemplateCode", sendParam.getTemplateCode());
        request.putQueryParameter("TemplateParam", sendParam.getTemplateParam());
        request.putQueryParameter("SmsUpExtendCode", sendParam.getSmsUpExtendCode());
        request.putQueryParameter("OutId", sendParam.getOutId());


        request.putQueryParameter("PhoneNumbers", "13036201053");
        request.putQueryParameter("SignName", "signfffff");
        request.putQueryParameter("TemplateCode", "ddd");
        request.putQueryParameter("TemplateParam", "{\"code\":\"22222\"}");
        request.putQueryParameter("SmsUpExtendCode", "4321sss");
        request.putQueryParameter("OutId", "123456");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void sendBatchSms(SmsBatchSendParam batchSendParam) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsSenderConfig.getDomain());
        request.setSysVersion(smsSenderConfig.getVersion());
        request.setSysAction("SendBatchSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumberJson", "[\"13098930293\",\"13909203945\"]");
        request.putQueryParameter("SignNameJson", "[\"ecefef\",\"ferferfefe\"]");
        request.putQueryParameter("TemplateCode", "ffffffff");
        request.putQueryParameter("TemplateParamJson", "[{\"code\":\"323323\"},{\"code\":\"343344\"}]");

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
