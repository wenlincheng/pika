package com.wenlincheng.pika.item.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.common.core.base.controller.BaseController;
import com.wenlincheng.pika.common.core.base.vo.Result;
import com.wenlincheng.pika.common.core.log.annotation.PikaLog;
import com.wenlincheng.pika.item.entity.form.item.ItemStepOneForm;
import com.wenlincheng.pika.item.entity.form.item.ItemStepTwoForm;
import com.wenlincheng.pika.item.entity.form.item.ItemPageQuery;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepOneVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepTwoVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemListVO;
import com.wenlincheng.pika.item.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Slf4j
@Api(value = "ItemController", tags = "商品")
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "分页查询", notes = "分页查询", httpMethod = "GET")
    @GetMapping("/page")
    public Result<IPage<ItemListVO>> queryPage(ItemPageQuery pageQuery) {
        log.debug("query with pageQuery:{}", pageQuery);
        IPage<ItemListVO> page = itemService.queryPageList(pageQuery);
        return Result.success(page);
    }

    @ApiOperation(value = "查询商品[Step1]", notes = "查询商品Step1", httpMethod = "GET")
    @GetMapping("/step1/{id}")
    public Result<ItemDetailStepOneVO> queryItemStepOne(@PathVariable Long id) {
        ItemDetailStepOneVO itemDetail = itemService.queryItemStepOne(id);
        return Result.success(itemDetail);
    }

    @ApiOperation(value = "查询商品[Step2]", notes = "查询商品Step2", httpMethod = "GET")
    @GetMapping("/step2/{id}")
    public Result<ItemDetailStepTwoVO> queryItemStepTwo(@PathVariable Long id) {
        ItemDetailStepTwoVO itemDetail = itemService.queryItemStepTwo(id);
        return Result.success(itemDetail);
    }

    @PikaLog("创建商品[Step1]")
    @ApiOperation(value = "创建商品[Step1]", notes = "创建商品Step1", httpMethod = "POST")
    @PostMapping("/step1")
    public Result<Long> createItemStepOne(ItemStepOneForm itemAddForm) {
        itemAddForm.setCreateUserId(this.currentUserId());
        Long itemId = itemService.createItemStepOne(itemAddForm);
        return Result.success(itemId);
    }

    @PikaLog("创建商品[Step2]")
    @ApiOperation(value = "创建商品[Step2]", notes = "创建商品Step2", httpMethod = "POST")
    @PostMapping("/step2")
    public Result<Boolean> createItemStepTwo(ItemStepTwoForm itemAddForm) {
        itemAddForm.setCreateUserId(this.currentUserId());
        return Result.success(itemService.createItemStepTwo(itemAddForm));
    }

    @PikaLog("编辑商品[Step1]")
    @ApiOperation(value = "编辑商品[Step1]", notes = "编辑商品Step1", httpMethod = "PUT")
    @PutMapping("/step1")
    public Result<Boolean> updateItemStepOne(ItemStepOneForm itemAddForm) {
        itemAddForm.setUpdateUserId(this.currentUserId());
        return Result.success(itemService.updateItemStepOne(itemAddForm));
    }

    @PikaLog("编辑商品[Step2]")
    @ApiOperation(value = "编辑商品[Step2]", notes = "编辑商品Step2", httpMethod = "PUT")
    @PutMapping("/step2")
    public Result<Boolean> updateItemStepTwo(ItemStepTwoForm itemAddForm) {
        itemAddForm.setUpdateUserId(this.currentUserId());
        return Result.success(itemService.updateItemStepTwo(itemAddForm));
    }

    @PikaLog("逻辑删除商品")
    @ApiOperation(value = "逻辑删除商品", notes = "逻辑删除商品", httpMethod = "DELETE")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteItemById(@PathVariable Long id) {
        return Result.success(itemService.deleteById(id));
    }

    @PikaLog("商品上架")
    @ApiOperation(value = "商品上架", notes = "商品上架", httpMethod = "GET")
    @GetMapping("/onShelf/{id}")
    public Result<Boolean> onShelf(@PathVariable Long id) {
        return Result.success(itemService.onShelf(id));
    }
}
