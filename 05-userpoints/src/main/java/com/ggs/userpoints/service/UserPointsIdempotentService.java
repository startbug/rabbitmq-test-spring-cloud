package com.ggs.userpoints.service;

import org.springframework.amqp.core.Message;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ggs.userpoints.entity.UserPointsIdempotent;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
public interface UserPointsIdempotentService extends IService<UserPointsIdempotent> {

    void consume(Message msg) throws InterruptedException;

}
