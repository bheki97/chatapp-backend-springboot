package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.smsverifier;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.CodeGenerator;
import com.fnb.bheki97.chatappbackendspringboot.generics.smser.SmsSender;
import com.fnb.bheki97.chatappbackendspringboot.model.SmsRequest;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.VerificationCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeekSmsVerifier implements VerificationCodeSender {

    @Autowired
    private CodeGenerator<Long> codeGenerator;

    @Autowired
    private SmsSender smsSender;

    public void sendGeekVerificationCode(Geek geek){

        SmsRequest request = new SmsRequest();
        request.setMessage(generateMessage(geek.getFirstname(),codeGenerator.generateCode(6)));
        request.setPhoneNumber(geek.getCellNumber());
        smsSender.sendSms(request);
    }

    private String generateMessage(String name , long code){
        String msg = "Hey %n, We have received a request that you want to register on our GeekSpace. " +
                "So use this verification code-%c to complete the verification and registration process.";
        return String.format(msg,name,code);
    }
}
