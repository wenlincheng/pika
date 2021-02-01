package com.wenlincheng.pika.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Created by zhouyuxin on 2018/4/13.
 */
public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    public static  final String RUNMOD="run_mod";
    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            // 要加载的属性文件
            in = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("application.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("vas.properties文件流关闭出现异常");
            }
        }
        logger.info("加载properties文件内容完成...........");
        logger.info("properties文件内容：" + props);
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    /**
     * 判断是否是生产环境
     *
     * @return
     */
    public static boolean isProd(){
        String rundMod=getProperty(RUNMOD);
        if("prod".equals(rundMod)){
            return true;
        }
        return false;
    }

}
