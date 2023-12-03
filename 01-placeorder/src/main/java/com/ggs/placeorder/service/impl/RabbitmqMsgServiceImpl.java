package com.ggs.placeorder.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ggs.placeorder.entity.RabbitmqMsg;
import com.ggs.placeorder.mapper.RabbitmqMsgMapper;
import com.ggs.placeorder.service.RabbitmqMsgService;

/**
 * <p>
 * 异常消息表,最多发送三次 服务实现类
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
@Service
public class RabbitmqMsgServiceImpl extends ServiceImpl<RabbitmqMsgMapper, RabbitmqMsg> implements RabbitmqMsgService {

}
