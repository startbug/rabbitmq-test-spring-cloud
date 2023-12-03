package com.ggs.placeorder.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/12/3 19:49
 */
@Configuration
public class RabbitMQConfig {

    public static final String PLACE_ORDER_EXCHANGE = "place_order_exchange";

    public static final String COUPON_QUEUE = "coupon_queue";

    public static final String USER_POINTS_QUEUE = "user_points_queue";

    public static final String BUSINESS_QUEUE = "business_queue";

    @Bean
    public Exchange placeOrderExchange() {
        return ExchangeBuilder.fanoutExchange(PLACE_ORDER_EXCHANGE).build();
    }

    @Bean
    public Queue couponQueue() {
        return QueueBuilder.durable(COUPON_QUEUE).build();
    }

    @Bean
    public Queue userPointsQueue() {
        return QueueBuilder.durable(USER_POINTS_QUEUE).build();
    }

    @Bean
    public Queue businessQueue() {
        return QueueBuilder.durable(BUSINESS_QUEUE).build();
    }

    @Bean
    public Binding couponBinding() {
        return BindingBuilder.bind(couponQueue()).to(placeOrderExchange()).with("").noargs();
    }

    @Bean
    public Binding userPointsBinding() {
        return BindingBuilder.bind(userPointsQueue()).to(placeOrderExchange()).with("").noargs();
    }

    @Bean
    public Binding businessBinding() {
        return BindingBuilder.bind(businessQueue()).to(placeOrderExchange()).with("").noargs();
    }

}
