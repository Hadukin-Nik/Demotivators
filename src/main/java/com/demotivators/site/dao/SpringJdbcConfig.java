package com.demotivators.site.dao;

import com.demotivators.site.configuration.PropertiesOfDBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.demotivators.site.dao")
public class SpringJdbcConfig {
    @Autowired
    PropertiesOfDBConnection propertiesOfDBConnection;
    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propertiesOfDBConnection.getDriverClassName());
        dataSource.setUrl(propertiesOfDBConnection.getUrl());
        dataSource.setUsername(propertiesOfDBConnection.getUsername());
        dataSource.setPassword(propertiesOfDBConnection.getPassword());

        return dataSource;
    }

}