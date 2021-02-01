package com.wenlincheng.pika.common.core.util.excel;

import com.csvreader.CsvReader;
import com.wenlincheng.pika.common.core.exception.BaseException;
import com.wenlincheng.pika.common.core.util.CheckUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.CSV_PARSING_FAIL;
import static com.wenlincheng.pika.common.core.exception.SystemErrorCodeEnum.CSV_PROPERTIES_DO_NOT_SUPPORT;

/**
 * Csv utils
 * <p>
 * Created by zhouyuxin on 2018/4/8.
 */
public class CsvUtils {

     /** CSV文件列分隔符 */
     private static final String CSV_COLUMN_SEPARATOR = ",";

     /** CSV文件列分隔符 */
     private static final String CSV_RN = "\r\n";

    /**
     * 解析csv文件返回对应对象
     *
     * @param file              文件
     * @param c                 类
     * @param correspondence    类的属性
     * @param skipHead          是否跳过表头
     * @return List<T>
     */
    public static <T> List<T> readCsv(MultipartFile file, Class<T> c, LinkedHashMap<Integer, String> correspondence, Boolean skipHead) {
        List<T> list = new ArrayList<T>();
        CsvReader reader = null;
        try {
            reader = new CsvReader(file.getInputStream(), StandardCharsets.UTF_8);
            if (skipHead) {
                reader.readHeaders();
            }
            while (reader.readRecord()) {
                T entity = c.newInstance();
                for (int i = 0; i < reader.getValues().length; i++) {
                    Field f = c.getDeclaredField(correspondence.get(i));
                    fillValue(f, reader.getValues()[i].trim(), entity);
                }
                list.add(entity);
            }

        } catch (Exception e) {
            throw new BaseException(CSV_PARSING_FAIL, e);
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return list;
    }

    /**
     * 填充字段
     *
     * @param f
     * @param value
     * @param entity
     * @throws IllegalAccessException
     */
    public static void fillValue(Field f, String value, Object entity) throws Exception {
        if (CheckUtils.isEmpty(value)) {
            return;
        }
        String xclass = f.getType().toString();
        f.setAccessible(true);
        if ("class java.lang.String".equals(xclass)) {
            f.set(entity, value);
        } else if ("class java.util.Date".equals(xclass)) {
            f.set(entity, new Date(value));
        } else if ("class java.lang.Boolean".equals(xclass)) {
            f.setBoolean(entity, "true".equals(value) ? true : false);
        } else if ("class java.lang.Integer".equals(xclass)) {
            f.setInt(entity, Integer.getInteger(value));
        } else if ("class java.lang.Long".equals(xclass)) {
            f.setLong(entity, Long.parseLong(value));
        } else if ("class java.lang.Double".equals(xclass)) {
            f.setDouble(entity, Double.parseDouble(value));
        } else {
            throw new BaseException(CSV_PROPERTIES_DO_NOT_SUPPORT);
        }
    }

    /**
     *
     * @param dataList 集合数据
     * @param colNamesArr 表头部数据
     * @param mapKeyArr 查找的对应数据
     */
    public static boolean doExport(List<Map<String, Object>> dataList, String[] colNamesArr, String[] mapKeyArr, OutputStream os) {
        try {
            StringBuffer buf = new StringBuffer();

            // 完成数据csv文件的封装
            // 输出列头
            for (int i = 0; i < colNamesArr.length; i++) {
                buf.append(colNamesArr[i]).append(CSV_COLUMN_SEPARATOR);
            }
            buf.append(CSV_RN);

            // 输出数据
            if (null != dataList) {
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeyArr.length; j++) {
                        buf.append(dataList.get(i).get(mapKeyArr[j])).append(CSV_COLUMN_SEPARATOR);
                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            os.write(buf.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
            return true;
        } catch (Exception e) {
            throw new BaseException(CSV_PARSING_FAIL, e);
        }
    }


    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        String fn = fileName +  ".csv";
        // 读取字符编码
        String utf = "UTF-8";
        // 设置响应
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));
    }

}
