package com.ggs.userpoints.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:29
 */
@RestController
public class UserPointsController {

    @GetMapping("/up")
    public void up() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("扣除用户积分成功！！");
    }

}
