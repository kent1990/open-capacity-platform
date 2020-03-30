package com.open.capacity.item.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ocp_item")
@Builder
public class OcpItem implements Serializable  {

    private String id;

    @TableField(value="product_id")
    private String productId;
    private Integer total;
    private Integer used;
    private Integer residue;


}
