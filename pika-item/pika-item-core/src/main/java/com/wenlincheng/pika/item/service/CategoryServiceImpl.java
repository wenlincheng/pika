package com.wenlincheng.pika.item.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.common.core.enums.YnEnum;
import com.wenlincheng.pika.item.entity.form.category.CategoryPageQuery;
import com.wenlincheng.pika.item.api.CategoryService;
import com.wenlincheng.pika.item.entity.po.Category;
import com.wenlincheng.pika.item.entity.vo.category.CategoryListVO;
import com.wenlincheng.pika.item.mapper.CategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 分类 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public IPage<CategoryListVO> queryPageList(CategoryPageQuery queryForm) {
        QueryWrapper<Category> queryWrapper = queryForm.buildWrapper();
        queryWrapper.lambda().
                like(StringUtils.isNotBlank(queryForm.getName()), Category::getName, queryForm.getName())
                .eq(Objects.nonNull(queryForm.getParentId()), Category::getParentId, queryForm.getParentId())
                .eq(Category::getIsDeleted, YnEnum.NO.getValue());
        IPage<Category> pageList = this.page(queryForm.getPage(), queryWrapper);

        return pageList.convert(CategoryListVO::new);
    }

    @Override
    public Category getById(Serializable id) {


        return super.getById(id);
    }

}
