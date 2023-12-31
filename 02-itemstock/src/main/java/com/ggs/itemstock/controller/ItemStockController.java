package com.ggs.itemstock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:26
 */
@RestController
public class ItemStockController {

    public static int stock = 10;

    @GetMapping("/decr")
    public void desc() throws InterruptedException {
        Thread.sleep(400);
        stock--;
        System.out.println("商品库存扣减成功");
        if (stock < 0) {
            throw new RuntimeException("商品库存不足");
        }
    }

}
