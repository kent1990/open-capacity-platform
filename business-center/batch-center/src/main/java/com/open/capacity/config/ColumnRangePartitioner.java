package com.open.capacity.config;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @create 2019年4月2日
 * Content :根据数据ID分片
 */

public class ColumnRangePartitioner implements Partitioner {

    private JdbcOperations jdbcTemplate;

    ColumnRangePartitioner(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
       
        Map<String, ExecutionContext> result = new LinkedHashMap<String, ExecutionContext>();
        int current_thread = 1 ;
        int total_thread     = gridSize ;
        while (current_thread <=  total_thread) {
            ExecutionContext value = new ExecutionContext();
            result.put("partition" + current_thread, value);
 
            value.putInt("current_thread", current_thread);
            value.putInt("total_thread", total_thread);
 
            current_thread++;
        }

        return result;
    	
    }
}
