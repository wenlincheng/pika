package com.wenlincheng.pika.item.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.item.entity.form.category.CategoryPageQuery;
import com.wenlincheng.pika.item.entity.vo.category.CategoryListVO;
import com.wenlincheng.pika.item.api.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 后台类目 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Api(value = "CategoryController", tags = "后台类目")
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/page")
    public Result<IPage<CategoryListVO>> queryPage(CategoryPageQuery categoryQueryForm) {
        IPage<CategoryListVO> page = categoryService.queryPageList(categoryQueryForm);
        return Result.success(page);
    }

}
