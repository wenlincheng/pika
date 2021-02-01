package com.wenlincheng.pika.upms.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单树
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class MenuTree extends TreeNode implements Serializable {
    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单名称
     */
    private String name;

    private boolean spread = false;

    /**
     * 前端路由标识路径
     */
    private String path;

    /**
     * 路由缓冲
     */
    private String keepAlive;

    /**
     * 权限编码
     */
    private String permission;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;

    /**
     * 菜单标签
     */
    private String label;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 是否包含子节点
     */
    private Boolean hasChildren;

    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.label = name;
    }

    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.label = name;
    }
}
