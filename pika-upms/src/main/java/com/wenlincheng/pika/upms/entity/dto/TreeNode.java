package com.wenlincheng.pika.upms.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@Data
public class TreeNode {
    protected int id;

    protected int parentId;

    protected List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
