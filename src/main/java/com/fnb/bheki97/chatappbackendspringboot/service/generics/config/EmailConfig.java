package com.fnb.bheki97.chatappbackendspringboot.service.generics.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailConfigProperties properties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Map<String,String>mail = properties.getSmtp();

        System.out.println(mail);
        mailSender.setHost(mail.get("host"));


        mailSender.setPort(toInteger(mail.get("port")));
        mailSender.setUsername(mail.get("username"));
        mailSender.setPassword(mail.get("password"));



        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", mail.get("auth"));
        props.put("mail.smtp.starttls.enable", mail.get("enable"));
        props.put("mail.smtp.host", mail.get("host"));
        props.put("mail.smtp.ssl.trust", mail.get("trust"));



        return mailSender;
    }

    private int toInteger(String val) throws RuntimeException{
        val =val.trim();

        if(val.matches("[0-9]+")){
            return Integer.parseInt(val);
        }
        throw new RuntimeException("String is not a number: "+val);
    }
}

 @Component
 @Data
 @ConfigurationProperties(prefix = "mail")
 @PropertySource(value = "classpath:email-config.properties")
 class EmailConfigProperties{
    private Map<String,String> smtp;
 }


