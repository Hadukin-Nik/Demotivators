package com.demotivators.site.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "meme.scroll")
public class MemeScrollProperties {
    private Long max_count_of_memes;
}
