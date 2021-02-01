package com.wenlincheng.pika.auth.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.constant.SecurityConstants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.SUCCESS;


/**
 * 返回处理工具类
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
public class ResponseUtil {

    /**
     * 使用response输出JSON
     *
     * @param response 返回
     * @param result 响应结果
     */
    public static void out(HttpServletResponse response, Result<?> result) {

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        JSONObject jsonObject = getJson(result.getCode(), result.getMsg(), result.getTime(), result.getData());

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        //response.setStatus(result.getCode());
        response.setStatus(SUCCESS.getCode());
        /*//统一用过滤器设置跨域
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");*/
        try {
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString(SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static <E> JSONObject getJson(int code, String msg, Date time, E data) {
        JSONObject json = new JSONObject();
        json.put(SecurityConstants.CODE, code);
        json.put(SecurityConstants.MSG, msg);
        json.put(SecurityConstants.DATA, data);
        json.put(SecurityConstants.TIME, time);

        return json;
    }
}
