package com.fnb.bheki97.chatappbackendspringboot.generics.htmlgenerator;

import java.util.Map;

public interface HtmlToStringGenerator {
    String generateHtml(Map<String, Object>templateValues,String templateFileName);
}
