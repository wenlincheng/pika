package com.wenlincheng.pika.item.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlincheng.pika.item.entity.form.item.ItemPageQuery;
import com.wenlincheng.pika.item.entity.form.item.ItemStepOneForm;
import com.wenlincheng.pika.item.entity.form.item.ItemStepTwoForm;
import com.wenlincheng.pika.item.entity.po.Item;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepOneVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemDetailStepTwoVO;
import com.wenlincheng.pika.item.entity.vo.item.ItemPageVO;
import com.wenlincheng.pika.item.mapper.ItemMapper;
import com.wenlincheng.pika.item.message.produce.Foo;
import com.wenlincheng.pika.item.message.produce.SenderService;
import com.wenlincheng.pika.item.api.ItemService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author Pikaman
 * @date 2021/1/1 10:10 上午
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private SenderService senderService;

    @Override
    public IPage<ItemPageVO> queryPageList(ItemPageQuery pageQuery) {
        QueryWrapper<Item> queryWrapper = pageQuery.buildWrapper();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(pageQuery.getName()), Item::getName, pageQuery.getName())
                .like(StringUtils.isNotBlank(pageQuery.getCode()), Item::getCode, pageQuery.getCode())
                .eq(StringUtils.isNotBlank(pageQuery.getSaleStatus()), Item::getSaleStatus, pageQuery.getSaleStatus())
                .orderByDesc(Item::getCreateTime);
        IPage<Item> itemPage = this.page(pageQuery.getPage(), queryWrapper);

        return itemPage.convert(ItemPageVO::new);
    }

    @Override
    public ItemDetailStepOneVO queryItemStepOne(Long id) {
        Item item = this.getById(id);
        ItemDetailStepOneVO itemDetail = new ItemDetailStepOneVO(item);
        // TODO

        return itemDetail;
    }

    @Override
    public ItemDetailStepTwoVO queryItemStepTwo(Long id) {
        Item item = this.getById(id);
        ItemDetailStepTwoVO itemDetail = new ItemDetailStepTwoVO(item);
        // TODO

        return itemDetail;
    }

    @GlobalTransactional(name = "item-service-create-order", rollbackFor = Exception.class)
    @Override
    public Long createItemStepOne(ItemStepOneForm itemAddForm) {
        Item item = initItem();
        // TODO 补全商品信息
        item.setName("华为P40")
                .setMainPicUrl("https://img.ternarytree.cn/upload/20210130223046.png")
                .setMaxLinePrice(new BigDecimal("10999"))
                .setMinLinePrice(new BigDecimal("8999"))
                .setMaxUnitPrice(new BigDecimal("9999"))
                .setMinUnitPrice(new BigDecimal("7999"));
        item.insert();

        return item.getId();
    }

    @Override
    public Boolean createItemStepTwo(ItemStepTwoForm itemAddForm) {

        return null;
    }

    @Override
    public Boolean updateItemStepOne(ItemStepOneForm itemForm) {
        Item item = itemForm.toPo(Item.class);
        // TODO
        return this.updateById(item);
    }

    @Override
    public Boolean updateItemStepTwo(ItemStepTwoForm itemForm) {
        Item item = itemForm.toPo(Item.class);

        // TODO
        return this.updateById(item);
    }

    @Override
    public Boolean deleteById(Long id) {
        Item item = new Item();
        item.setId(id);
        // item.setId(id).setIsDeleted(YnEnum.YES.getValue());
        // TODO
        return this.removeById(item);
    }

    @Override
    public Boolean onShelf(Long id) {

        try {
            senderService.send("商品上架");
            Foo foo = new Foo(1000, "商品上架");
            senderService.sendObject(foo, "tagObj");
            senderService.sendTransactionalMsg(foo, 2);
            senderService.sendWithTags(foo, "tagObj");

        } catch (Exception e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 扣减库存
     *
     * 服务提供方本地事务注解
     *
     * @param id 商品id
     * @return java.lang.Boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean stock(Long id) {
        Item item = initItem();
        this.save(item);
        //int i = 10/0;
        return true;
    }

    /**
     * 初始化商品
     */
    private Item initItem() {
        Item item = new Item();
        return item;
    }




}
