package com.ggs.placeorder.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ggs.placeorder.entity.RabbitmqMsg;

/**
 * <p>
 * 异常消息表,最多发送三次 服务类
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
public interface RabbitmqMsgService extends IService<RabbitmqMsg> {

}
