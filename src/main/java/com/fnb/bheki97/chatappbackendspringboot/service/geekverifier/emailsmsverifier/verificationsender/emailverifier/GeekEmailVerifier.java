package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.verificationsender.emailverifier;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.CodeGenerator;
import com.fnb.bheki97.chatappbackendspringboot.generics.emailer.EmailSender;
import com.fnb.bheki97.chatappbackendspringboot.generics.htmlgenerator.HtmlToStringGenerator;
import com.fnb.bheki97.chatappbackendspringboot.model.EmailMessage;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.verificationsender.VerificationCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeekEmailVerifier implements VerificationCodeSender {


    @Autowired
    private EmailSender emailSender;
    @Autowired
    private HtmlToStringGenerator htmlGenerator;
    @Autowired
    private CodeGenerator<String> codeGenerator;


    @Override
    public Object sendGeekVerificationCode(Geek geek){
        EmailMessage message = new EmailMessage();
        message.setReceivers(new String[]{geek.getEmail()});
        message.setHtml(true);
        message.setSubject("Email Verification Code");

        String code = codeGenerator.generateCode(6);
        Map<String,Object> values =new HashMap<>();
        values.put("code",code);
        values.put("geek",geek);

        String html = htmlGenerator.generateHtml(values,"verif-code-email");
        message.setMessage(html);

        emailSender.sendEmail(message);

        return code;

    }

}
