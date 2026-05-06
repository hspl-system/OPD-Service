package com.healthify.opdservice.config.dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.catalina.valves.JDBCAccessLogValve;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public HikariConfig opdDataSourceConfig(){
        HikariConfig opdDataSourceConfig = new HikariConfig();
        opdDataSourceConfig.setJdbcUrl("jdbc:mysql://localhost:3306/opdservicedb");
        opdDataSourceConfig.setUsername("opd_user");
        opdDataSourceConfig.setPassword("opd123");
        opdDataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return opdDataSourceConfig;
    }

    @Bean
    public  HikariDataSource opdDataSource(){
        HikariDataSource opdDataSource = new HikariDataSource(opdDataSourceConfig());
        return opdDataSource;
    }

    @Bean
    public JdbcTemplate opdJdbcTemplate(){
        JdbcTemplate opdJdbcTemplate = new JdbcTemplate(opdDataSource());
        return opdJdbcTemplate;
    }

    @Bean
    public HikariConfig userDataSourceConfig(){
        HikariConfig userDataSourceConfig = new HikariConfig();
        userDataSourceConfig.setJdbcUrl("jdbc:mysql://localhost:3306/authDb");
        userDataSourceConfig.setUsername("admin");
        userDataSourceConfig.setPassword("admin123");
        userDataSourceConfig.setDataSourceClassName("com.mysql.cj.jdbc.Driver");

        return userDataSourceConfig;
    }

    @Bean
    public HikariDataSource userDataSource(){
        HikariDataSource userDataSource = new HikariDataSource(userDataSourceConfig());
        return userDataSource;
    }


}
