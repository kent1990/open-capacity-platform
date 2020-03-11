package com.open.capacity.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ocp_tql")
@Builder
public class OcpTql {


    private String id;

    @TableField(value="user_id")
    private String userId;


    private Integer money;

}
