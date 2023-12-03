package com.ggs.placeorder.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.placeorder.clients.BusinessClient;
import com.ggs.placeorder.clients.CouponClient;
import com.ggs.placeorder.clients.ItemStockClient;
import com.ggs.placeorder.clients.OrderManageClient;
import com.ggs.placeorder.clients.UserPointsClient;
import com.ggs.placeorder.config.RabbitMQConfig;
import com.ggs.placeorder.util.GlobalCache;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:23
 */
@RestController
public class PlaceOrderController {

    @Autowired
    private BusinessClient businessClient;

    @Autowired
    private CouponClient couponClient;

    @Autowired
    private ItemStockClient itemStockClient;

    @Autowired
    private OrderManageClient orderManageClient;

    @Autowired
    private UserPointsClient userPointsClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟用户下单操作(优化前)
     */
//    @GetMapping("/po")
//    public String po() {
//        long start = System.currentTimeMillis();
//
//        // 1. 调用库存服务扣减商品库存
//        itemStockClient.desc();
//
//        // 2.调用订单服务，创建订单
//        orderManageClient.create();
//
//        // 3.调用优惠券服务，预扣除使用的优惠券
//        couponClient.coupon();
//
//        // 4.调用用户积分服务，预扣除用户使用的积分
//        userPointsClient.up();
//
//        // 5.调用商家服务，通知商家用户已下单
//        businessClient.notifyBusiness();
//
//        long end = System.currentTimeMillis();
//
//        System.out.println(end - start + "ms");
//
//        return "place order is ok!";
//    }

    /**
     * 模拟用户下单操作(优化后)
     */
    @GetMapping("/po")
    public String po() {
        long start = System.currentTimeMillis();

        // 1. 调用库存服务扣减商品库存
        itemStockClient.desc();

        // 2.调用订单服务，创建订单
        orderManageClient.create();

//        // 3.调用优惠券服务，预扣除使用的优惠券
//        couponClient.coupon();
//        // 4.调用用户积分服务，预扣除用户使用的积分
//        userPointsClient.up();
//        // 5.调用商家服务，通知商家用户已下单
//        businessClient.notifyBusiness();
        String msg = "用户信息&优惠券信息&订单信息等等....";

        // 声明当前消息的id标识
//        String msgId = UUID.randomUUID().toString().replaceAll("-", "");
        String msgId = "dslkfjldskjflkajofiuwoierj";
        // 消息的内容自己封装
        Map<String, Object> map = new HashMap();
        map.put("message", msg);
        map.put("exchange", RabbitMQConfig.PLACE_ORDER_EXCHANGE);
        map.put("routingKey", "");
        map.put("sendTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 将id和消息关联起来，存到全局缓存中
        GlobalCache.set(msgId, map);
        // 将同步方式修改为基于RabbitMQ的异步方式
        rabbitTemplate.convertAndSend(RabbitMQConfig.PLACE_ORDER_EXCHANGE, "", msg.getBytes(), new CorrelationData(msgId));

        long end = System.currentTimeMillis();

        System.out.println(end - start + "ms");

        return "place order is ok!";
    }

}
