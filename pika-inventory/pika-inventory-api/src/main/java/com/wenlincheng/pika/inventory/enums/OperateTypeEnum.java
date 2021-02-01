package com.wenlincheng.pika.inventory.enums;

import com.wenlincheng.pika.common.core.enums.IEnum;
import lombok.Getter;

/**
 * 操作状态
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Getter
public enum OperateTypeEnum implements IEnum<String> {

    REDUCE_USABLE("REDUCE_USABLE", "减少可用", "减少可用"),
    INCREASE_USABLE("INCREASE_USABLE", "增加可用", "增加可用"),

    OCC_USABLE("OCC_USABLE", "增加占用", "增加占用"),
    CANCEL_OCC("CANCEL_OCC", "释放占用", "释放占用"),
    REDUCE_OCC("REDUCE_OCC", "扣除占用", "扣除占用"),
    //仓库盘亏,扣光仓库可用和库区可用后,还是不够,强制从库区的预占中扣除
    //之所以要区分,是因为一个盘点单,可能要扣库区的可用和预占,用一个操作日志的索引就冲突了
    REDUCE_OCC_FORCE("REDUCE_OCC_FORCE", "强制扣除占用", "强制扣除占用"),

    FREEZE_USABLE("FREEZE_USABLE", "冻结可用", "冻结可用"),
    CANCEL_FREEZE("CANCEL_FREEZE", "释放冻结", "释放冻结"),
    REDUCE_FREEZE("REDUCE_FREEZE", "扣除冻结", "扣除冻结"),

    USE_OVERDRAFT("USE_OVERDRAFT","使用未分配库存","使用未分配库存"),
    RETURN_OVERDRAFT("RETURN_OVERDRAFT","回退使用未分配库存","回退使用未分配库存");

    private String value;
    private String name;
    private String help;

    OperateTypeEnum(String  value, String name, String help) {
        this.value = value;
        this.name = name;
        this.help = help;
    }
}
