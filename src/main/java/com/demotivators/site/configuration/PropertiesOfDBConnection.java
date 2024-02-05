package com.demotivators.site.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:connectionDB.properties")
@Getter
public class PropertiesOfDBConnection {

    @Value( "${dataSource.driverClassName}" )
    private String driverClassName;

    @Value( "${datasource.url}" )
    private String url;

    @Value( "${datasource.username}" )
    private String username;

    @Value( "${datasource.password}" )
    private String password;

}