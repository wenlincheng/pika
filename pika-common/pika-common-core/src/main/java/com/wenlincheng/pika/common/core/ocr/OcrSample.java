package com.wenlincheng.pika.common.core.ocr;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * OCR
 *
 * @author pikaman
 * @version 1.0.0
 * @date 2021/4/29 10:28 下午
 */
public class OcrSample {

    //设置APPID/AK/SK
    public static final String APP_ID = "24088229";
    public static final String API_KEY = "cNFl9Nb1MtKolfaI5KfKdwLn";
    public static final String SECRET_KEY = "W7QD1sulLhG4kmdbbkdZOCqvcvs3eUwT";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(20000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        //String path = "/Users/wenlincheng/Desktop/温林成/图像识别/摇号结果.jpeg";
        String path = "/Users/wenlincheng/Desktop/温林成/图像识别/登记.jpeg";
        JSONObject res = client.vatInvoice(path, new HashMap<>());
        System.out.println(res.toString(2));

    }

}
