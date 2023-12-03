package com.ggs.userpoints.service.impl;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ggs.userpoints.entity.UserPointsIdempotent;
import com.ggs.userpoints.mapper.UserPointsIdempotentMapper;
import com.ggs.userpoints.service.UserPointsIdempotentService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
@Service
@Slf4j
public class UserPointsIdempotentServiceImpl extends ServiceImpl<UserPointsIdempotentMapper, UserPointsIdempotent>
        implements UserPointsIdempotentService {

    @Autowired
    private UserPointsIdempotentMapper userPointsIdempotentMapper;

    public final String ID_NAME = "spring_returned_message_correlation";

    @Override
    public void consume(Message message) throws InterruptedException {
        // 1.查询幂等表是否存在当前消息标识
        String correlationId = message.getMessageProperties().getHeader(ID_NAME);
        int count = userPointsIdempotentMapper.countById(correlationId);

        // 2.如果存在，直接return
        if (count > 0) {
            log.info("消息已经被消费!!!无需重复消费");
            return;
        }

        // 3.如果不存在，插入消息标识到幂等表
        UserPointsIdempotent userPointsIdempotent = new UserPointsIdempotent();
        userPointsIdempotent.setId(correlationId);
        userPointsIdempotent.setCreateTime(LocalDateTime.now());
        userPointsIdempotentMapper.insert(userPointsIdempotent);

        // 4.执行消费逻辑,预扣除优惠券
        TimeUnit.MILLISECONDS.sleep(400);
        System.out.println("扣除用户积分成功!!!" + new String(message.getBody()));
    }

}
