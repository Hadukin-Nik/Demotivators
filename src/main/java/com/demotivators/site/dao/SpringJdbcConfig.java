package com.demotivators.site.dao;

import com.demotivators.site.configuration.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.demotivators.site.dao")
@RequiredArgsConstructor
public class SpringJdbcConfig {

    private final DataSource dataSource;
    @Bean
    public javax.sql.DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.dataSource.getDriverClassName());
        dataSource.setUrl(this.dataSource.getUrl());
        dataSource.setUsername(this.dataSource.getUsername());
        dataSource.setPassword(this.dataSource.getPassword());

        return dataSource;
    }

}