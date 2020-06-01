package com.open.capacity.report.config;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@ImportResource("classpath:ureport-console-context.xml")//不加项目能够启动但是会导致加载数据源报错或加载不了
@Configuration
public class UreportConfig implements BuildinDatasource {

    @Resource
    DataSource dataSource;

    @Override
    public String name() {
        return "ureportConfig";
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean //定义ureport的启动servlet
    @SuppressWarnings("unchecked")
    public ServletRegistrationBean ureportServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UReportServlet());
        servletRegistrationBean.addUrlMappings("/ureport/*");
        return servletRegistrationBean;
    }

}
