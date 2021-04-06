package com.wenlincheng.pika.schedule.controller;

import com.wenlincheng.pika.common.core.base.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author Pikaman
 * @version 1.0.0
 * @date 2021/1/1 10:10 上午
 */
@RequestMapping("/item")
@RestController
public class ItemJobController {

    @GetMapping("/on-sale/{id}")
    public Result<Boolean> createItemAutoOnSaleJob(@PathVariable Long id) {

        return Result.success();
    }

}
