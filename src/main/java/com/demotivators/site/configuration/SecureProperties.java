package com.demotivators.site.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {
    private String auth_token_header_name;
    private String auth_token;
}
