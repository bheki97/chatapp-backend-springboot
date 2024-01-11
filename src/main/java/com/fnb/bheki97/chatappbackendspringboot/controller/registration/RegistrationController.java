package com.fnb.bheki97.chatappbackendspringboot.controller.registration;

import com.fnb.bheki97.chatappbackendspringboot.dto.EmailSmsCodeDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.GeekVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.registration.GeekRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private GeekVerifier verifier;
    @Autowired
    private GeekRegistration registration;

    @PostMapping
    public boolean completeRegistration(@RequestBody Geek geek){
        System.out.println(geek);
        return geek!=null;
    }
    @PostMapping("/verify")
    public EmailSmsCodeDto verifyGeekInfo(@RequestBody Geek geek){
        EmailSmsCodeDto dto = new EmailSmsCodeDto<>();
        System.out.println(geek);
        dto.setEmailCode("d352df");
        dto.setSmsCode(23423);
        if(geek.getFirstname().equals("Bheki")){
            throw new ChatAppException("Bheki already exists");
        }


        return dto;
    }


}
