package com.ggs.userpoints.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggs.userpoints.entity.UserPointsIdempotent;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
public interface UserPointsIdempotentMapper extends BaseMapper<UserPointsIdempotent> {

    @Select("select count(1) from user_points_idempotent where id = #{id} ")
    int countById(@Param("id") String id);

}
