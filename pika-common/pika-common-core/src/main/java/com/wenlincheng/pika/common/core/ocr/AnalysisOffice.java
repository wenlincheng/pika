package com.wenlincheng.pika.common.core.ocr;

import java.net.URLEncoder;

/**
 * TODO
 *
 * @author lincheng.wen@daddylab.com
 * @version 1.0.0
 * @date 2021/4/29 10:54 上午
 */
public class AnalysisOffice {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String analysisOffice() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis_office";
        try {
            // 本地文件路径
            String filePath = "/Users/wenlincheng/Desktop/温林成/图像识别/登记2.png";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.6fcf425feec01afe62af81c4c15ff4b1.2592000.1622257964.282335-24088229";

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //String token = AuthService.getAuth("cNFl9Nb1MtKolfaI5KfKdwLn", "W7QD1sulLhG4kmdbbkdZOCqvcvs3eUwT");
        //System.out.println(token);

        AnalysisOffice.analysisOffice();
    }
}
