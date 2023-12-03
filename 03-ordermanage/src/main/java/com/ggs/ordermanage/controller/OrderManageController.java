package com.ggs.ordermanage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:26
 */
@RestController
public class OrderManageController {

    @GetMapping("/create")
    public void create() throws InterruptedException {
        Thread.sleep(400);
        System.out.printf("创建订单成功");
    }

}
