package com.ggs.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:30
 */
@RestController
public class BusinessController {

    @GetMapping("/notify")
    public void notifyBusiness() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("通知商家成功！！");
    }

}
