package com.wenlincheng.pika.common.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 序列化 BigDecimal 保留两位小数
 *
 * @author : Pikaman
 * @version : 1.0.0
 * @date : 2021/1/31 1:50 下午
 */
public class BigDecimalSerialize extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            jsonGenerator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_DOWN) + "");
        } else {
            jsonGenerator.writeString(null + "");
        }
    }
}
