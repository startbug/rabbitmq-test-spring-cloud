package com.ggs.placeorder.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ggs.placeorder.entity.RabbitmqMsg;
import com.ggs.placeorder.mapper.RabbitmqMsgMapper;
import com.ggs.placeorder.service.RabbitmqMsgService;
import com.ggs.placeorder.util.GlobalCache;

import lombok.extern.slf4j.Slf4j;


/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 21:32
 */
@Slf4j
@Configuration
public class RabbitTemplateConfig {

    @Autowired
    private RabbitmqMsgMapper rabbitmqMsgMapper;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // 1.new一个RabbitTemplate对象
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        // 2.将connectionFactory设置到RabbitTemplate对象中
        rabbitTemplate.setConnectionFactory(connectionFactory);

        // 3.设置confirm回调
        rabbitTemplate.setConfirmCallback(confirmCallback());

        // 4.设置return回调
        rabbitTemplate.setReturnCallback(returnCallback());

        // 5.设置mandatory为true
        // https://docs.spring.io/spring-amqp/reference/html/#template-confirms
        // mandatory需要将mandatory设置为true，才会启用return机制
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return new ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (correlationData == null) {
                    return;
                }
                String msgId = correlationData.getId();
                if (!ack) {
                    log.info("消息发送到Exchange成功!!!msgId = " + msgId);
                    // 消息成功发送到交换机之后，将消息从全局缓存中删除掉
                    GlobalCache.remove(msgId);
                } else {
                    log.info("消息发送到Exchange失败!!!msgId = " + msgId);
                    Map value = (Map) GlobalCache.get(msgId);
                    RabbitmqMsg rabbitmqMsg = new RabbitmqMsg();
                    rabbitmqMsg.setMessage((String) value.get("message"));
                    rabbitmqMsg.setExchange((String) value.get("exchange"));
                    rabbitmqMsg.setRoutingKey((String) value.get("routingKey"));
                    rabbitmqMsg.setSendTime(LocalDateTime.parse((String) value.get("sendTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    rabbitmqMsgMapper.insert(rabbitmqMsg);
                }
            }
        };
    }

    public RabbitTemplate.ReturnCallback returnCallback() {
        return new ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("消息未路由到队列");
                System.out.println("return消息为:" + new String(message.getBody()));
                System.out.println("return交换机为:" + exchange);
                System.out.println("return路由为:" + routingKey);
            }
        };
    }

}
