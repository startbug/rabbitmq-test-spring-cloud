package com.ggs.coupon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:28
 */
@RestController
public class CouponController {

    @GetMapping("/coupon")
    public void coupon() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("优惠券预扣除成功！");
    }

}
