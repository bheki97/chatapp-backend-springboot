package com.fnb.bheki97.chatappbackendspringboot.config.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "twilio")
@PropertySource(value = "classpath:sms-config.properties")
public class SmsConfigProperties {

    private String sid;
    private String token;
    private String phoneNumber;
}
