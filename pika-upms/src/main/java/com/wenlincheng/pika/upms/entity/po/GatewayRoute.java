package com.wenlincheng.pika.upms.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 网关路由表
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gateway_route")
public class GatewayRoute extends Model<GatewayRoute> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 路由id
     */
    @TableField("route_id")
    private String routeId;

    /**
     * uri路径
     */
    @TableField("uri")
    private String uri;

    /**
     * 判定器
     */
    @TableField("predicates")
    private String predicates;

    /**
     * 过滤器
     */
    @TableField("filters")
    private String filters;

    /**
     * 排序
     */
    @TableField("seq")
    private Integer seq;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 状态 0 无效 1 有效
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField("create_user_id")
    private Date createUserId;

    /**
     * 更新时间
     */
    @TableField("update_user_id")
    private Date updateUserId;

    /**
     * 逻辑删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
