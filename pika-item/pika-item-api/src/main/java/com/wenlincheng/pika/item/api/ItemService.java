package com.wenlincheng.pika.item.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wenlincheng.pika.item.entity.form.item.ItemPageQuery;
import com.wenlincheng.pika.item.entity.form.item.ItemStepOneForm;
import com.wenlincheng.pika.item.entity.form.item.ItemStepTwoForm;
import com.wenlincheng.pika.item.entity.po.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepOneVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepTwoVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemListVO;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
public interface ItemService extends IService<Item> {

    /**
     * 分页查询
     *
     * @param pageQuery    分页查询条件
     * @return IPage<ItemListVO>
     */
    IPage<ItemListVO> queryPageList(ItemPageQuery pageQuery);

    /**
     * 查询商品[第一步]
     *
     * @param id
     * @return java.lang.Long
     */
    ItemDetailStepOneVO queryItemStepOne(Long id);

    /**
     * 查询商品[第二步]
     *
     * @param id
     * @return java.lang.Boolean
     */
    ItemDetailStepTwoVO queryItemStepTwo(Long id);

    /**
     * 创建商品[第一步]
     *
     * @param itemForm
     * @return java.lang.Long
     */
    Long createItemStepOne(ItemStepOneForm itemForm);

    /**
     * 创建商品[第二步]
     *
     * @param itemForm
     * @return java.lang.Boolean
     */
    Boolean createItemStepTwo(ItemStepTwoForm itemForm);

    /**
     * 更新商品[第一步]
     *
     * @param itemForm
     * @return java.lang.Long
     */
    Boolean updateItemStepOne(ItemStepOneForm itemForm);

    /**
     * 更新商品[第二步]
     *
     * @param itemForm
     * @return java.lang.Boolean
     */
    Boolean updateItemStepTwo(ItemStepTwoForm itemForm);

    /**
     * 逻辑删除商品
     *
     * @param id 商品id
     * @return java.lang.Boolean
     */
    Boolean deleteById(Long id);

    /**
     * 商品上架
     *
     * @param id 商品id
     * @return java.lang.Boolean
     */
    Boolean onShelf(Long id);

    Boolean stock(Long id);
}
