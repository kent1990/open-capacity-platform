package com.open.capacity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.open.capacity.entity.DeliverPost;

public class DeliverPostRowMapper  implements RowMapper<DeliverPost> {

    /**
     * rs一条结果集，rowNum代表当前行
     */
    @Override
    public DeliverPost mapRow(ResultSet rs, int rowNum) throws SQLException {
    	DeliverPost deliverPost = new DeliverPost() ;
    	
    	deliverPost.setOrderId(rs.getString("order_id"));
    	deliverPost.setPostId(rs.getString("post_id"));
    	
        return deliverPost;
    }

}