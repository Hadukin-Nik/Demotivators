package com.demotivators.site.dao;

import com.demotivators.site.configuration.DataSourcePostgres;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.demotivators.site.dao")
@RequiredArgsConstructor
public class JdbcConfig {

    private final DataSourcePostgres dataSourcePostgres;
    @Bean
    public javax.sql.DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.dataSourcePostgres.getDriverClassName());
        dataSource.setUrl(this.dataSourcePostgres.getUrl());
        dataSource.setUsername(this.dataSourcePostgres.getUsername());
        dataSource.setPassword(this.dataSourcePostgres.getPassword());

        return dataSource;
    }

}