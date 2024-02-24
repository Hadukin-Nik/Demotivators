package com.demotivators.site.configuration;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DataSourcePostgresProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

}