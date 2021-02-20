package com.wenlincheng.pika.common.resource.file.alioss.entity;

import lombok.Data;

import java.util.List;

/**
 * RefererEntity
 *
 * @author  wenlincheng
 * @date  2019-09-06 13:21
 * @version 1.0
 */
@Data
public class RefererEntity extends AbstractEntity {

    List<String> refererList;

    public RefererEntity(String bucketName) {
        super(bucketName);
    }

    public void setRefererList(List<String> refererList) {
        this.refererList = refererList;
    }
}
