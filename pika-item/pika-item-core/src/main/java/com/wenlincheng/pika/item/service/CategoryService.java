package com.wenlincheng.pika.item.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.item.entity.form.category.CategoryPageQuery;
import com.wenlincheng.pika.item.entity.po.Category;
import com.wenlincheng.pika.item.entity.vo.CategoryListVO;

/**
 * <p>
 * 分类 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询
     *
     * @param categoryQueryForm
     * @return IPage<CategoryListVO>
     */
    IPage<CategoryListVO> queryPageList(CategoryPageQuery categoryQueryForm);

}
