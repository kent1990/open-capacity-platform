package com.open.capacity.order.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ocp_order")
@Builder
public class OcpOrder implements Serializable {

    private String id;

    @TableField(value="order_sn")
    private String orderSn;
    @TableField(value="user_id")
    private String userId;
    @TableField(value="create_time")
    private Date createTime;



}
