package com.ggs.placeorder.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * <p>
 * 异常消息表,最多发送三次
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
@Data
@TableName("rabbitmq_msg")
public class RabbitmqMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String message;

    private String exchange;

    private String routingKey;

    private LocalDateTime sendTime;

    private Integer sendCount;

    private Integer isSend;

}
