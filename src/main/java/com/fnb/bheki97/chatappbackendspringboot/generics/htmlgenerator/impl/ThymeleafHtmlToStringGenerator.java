package com.fnb.bheki97.chatappbackendspringboot.generics.htmlgenerator.impl;

import com.fnb.bheki97.chatappbackendspringboot.generics.htmlgenerator.HtmlToStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class ThymeleafHtmlToStringGenerator implements HtmlToStringGenerator {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String generateHtml(Map<String, Object> templateValues, String templateFileName) {
        Context context = new Context();

        templateValues.forEach(context::setVariable);

        return templateEngine.process(templateFileName,context);
    }
}
