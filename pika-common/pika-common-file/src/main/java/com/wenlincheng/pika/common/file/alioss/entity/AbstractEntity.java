package com.wenlincheng.pika.common.file.alioss.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity抽象类
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractEntity {
    private String bucketName;

    public AbstractEntity() {
    }

    public AbstractEntity(String bucketName) {
        this.bucketName = bucketName;
    }
}
