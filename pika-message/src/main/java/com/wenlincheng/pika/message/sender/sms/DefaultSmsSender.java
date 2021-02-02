package com.wenlincheng.pika.message.sender.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.message.config.SmsSenderConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static com.wenlincheng.pika.message.enums.MessageErrorCodeEnum.SMS_SEND_ERROR;

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
        request.putQueryParameter("RegionId", smsSenderConfig.getRegionId());
        request.putQueryParameter("PhoneNumbers", sendParam.getPhoneNumbers());
        request.putQueryParameter("SignName", sendParam.getSignName());
        request.putQueryParameter("TemplateCode", sendParam.getTemplateCode());
        request.putQueryParameter("TemplateParam", sendParam.getTemplateParam());
        request.putQueryParameter("SmsUpExtendCode", sendParam.getSmsUpExtendCode());
        request.putQueryParameter("OutId", sendParam.getOutId());

        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String code = (String) jsonObject.get("Code");
            if (!"OK".equals(code)) {
                throw new BaseException(SMS_SEND_ERROR);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BaseException(SMS_SEND_ERROR);
        }
    }

    @Override
    public void sendBatchSms(SmsBatchSendParam batchSendParam) {
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsSenderConfig.getDomain());
        request.setSysVersion(smsSenderConfig.getVersion());
        request.setSysAction("SendBatchSms");
        request.putQueryParameter("RegionId", smsSenderConfig.getRegionId());
        request.putQueryParameter("PhoneNumberJson", batchSendParam.getPhoneNumberJson());
        request.putQueryParameter("SignNameJson", batchSendParam.getSignNameJson());
        request.putQueryParameter("TemplateCode", batchSendParam.getTemplateCode());
        request.putQueryParameter("TemplateParamJson", batchSendParam.getTemplateParamJson());

        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            JSONObject jsonObject = JSON.parseObject(data);
            String code = (String) jsonObject.get("Code");
            if (!"OK".equals(code)) {
                throw new BaseException(SMS_SEND_ERROR);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new BaseException(SMS_SEND_ERROR);
        }
    }
}
