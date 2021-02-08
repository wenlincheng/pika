package com.wenlincheng.pika.common.core.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 带乐观锁的实体
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class VersionModel<T extends VersionModel<?>> extends IdModel<T> {

    /**
     * 乐观锁
     */
    private Long optVersion;

}
