package com.wenlincheng.pika.item.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 商品销售状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum ItemSaleStatusEnum implements IEnum<String> {

    UN_SALE("UN_SALE", "未上架", "未上架"),
    ON_SALE("ON_SALE", "已上架", "已上架"),
    NO_SALE("NO_SALE", "已下架", "已下架"),
    UP_APPLY("UP_APPLY", "上架申请", "上架申请"),
    DOWN_APPLY("DOWN_APPLY", "下架申请", "下架申请"),
    FORCE_NO_SALE("FORCE_NO_SALE","强制下架","强制下架")
    ;
    @EnumValue
    private String value;

    private String name;

    private String help;

    ItemSaleStatusEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }

}
