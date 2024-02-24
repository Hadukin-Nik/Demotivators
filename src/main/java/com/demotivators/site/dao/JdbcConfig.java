package com.demotivators.site.dao;

import com.demotivators.site.configuration.DataSourcePostgresProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcConfig {

    private final DataSourcePostgresProperties dataSourcePostgresProperties;
    @Bean
    public javax.sql.DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.dataSourcePostgresProperties.getDriverClassName());
        dataSource.setUrl(this.dataSourcePostgresProperties.getUrl());
        dataSource.setUsername(this.dataSourcePostgresProperties.getUsername());
        dataSource.setPassword(this.dataSourcePostgresProperties.getPassword());

        return dataSource;
    }

}