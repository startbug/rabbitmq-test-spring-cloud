package com.ggs.userpoints.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author lhh
 * @since 2023-12-03
 */
@Data
@TableName("user_points_idempotent")
public class UserPointsIdempotent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private LocalDateTime createTime;

}
