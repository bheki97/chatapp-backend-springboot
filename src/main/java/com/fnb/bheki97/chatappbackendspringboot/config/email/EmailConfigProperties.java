package com.fnb.bheki97.chatappbackendspringboot.config.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "mail")
@PropertySource(value = "classpath:email-config.properties")
public class EmailConfigProperties {
    private Map<String, String> smtp;
}
