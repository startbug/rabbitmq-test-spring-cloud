package com.ggs.placeorder.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggs.placeorder.entity.RabbitmqMsg;

/**
 * <p>
 * 异常消息表,最多发送三次 Mapper 接口
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
public interface RabbitmqMsgMapper extends BaseMapper<RabbitmqMsg> {

}
