package com.demotivators.site.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
public class DataSource {

    @Value( "${spring.dataSource.driverClassName}" )
    private String driverClassName;

    @Value( "${spring.datasource.url}" )
    private String url;

    @Value( "${spring.datasource.username}" )
    private String username;

    @Value( "${spring.datasource.password}" )
    private String password;

}