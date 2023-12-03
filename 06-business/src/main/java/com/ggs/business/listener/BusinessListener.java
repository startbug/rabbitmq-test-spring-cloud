package com.ggs.business.listener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ggs.business.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:59
 */
@Component
public class BusinessListener {

    @RabbitListener(queues = {RabbitMQConfig.BUSINESS_QUEUE})
    public void consume(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        // 预扣除优惠券
        TimeUnit.MILLISECONDS.sleep(400);
        System.out.println("通知商家成功成功!!!" + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
