package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wenlincheng.pika.common.core.base.model.IdModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_address")
public class UserAddress extends IdModel<UserAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 手机号
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 省市区街道编码
     */
    @TableField("area_code")
    private Integer areaCode;

    /**
     * 邮政编码
     */
    @TableField("postal_code")
    private String postalCode;

    /**
     * 详细地址：如门牌号、小区、楼栋号、单元室等
     */
    @TableField("address_detail")
    private String addressDetail;

    /**
     * 标签 如：家、公司、学校等
     */
    @TableField("tag")
    private String tag;

    /**
     * 是否默认 0 否 1 是
     */
    @TableField("is_default")
    private Integer isDefault;

}
