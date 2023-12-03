package com.ggs.userpoints.listener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ggs.userpoints.config.RabbitMQConfig;
import com.ggs.userpoints.service.UserPointsIdempotentService;
import com.rabbitmq.client.Channel;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:59
 */
@Component
public class UserPointsListener {

    @Autowired
    private UserPointsIdempotentService userPointsIdempotentService;

    @RabbitListener(queues = {RabbitMQConfig.USER_POINTS_QUEUE})
    public void consume(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        userPointsIdempotentService.consume(message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
