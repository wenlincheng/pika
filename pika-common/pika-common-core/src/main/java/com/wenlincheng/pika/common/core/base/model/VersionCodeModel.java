package com.wenlincheng.pika.common.core.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 带乐观锁、编码的实体
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public abstract class VersionCodeModel<T extends CodeModel<?>> extends CodeModel<T> {

    /**
     * 乐观锁
     */
    private Long optVersion;
}
