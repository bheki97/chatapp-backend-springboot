package com.fnb.bheki97.chatappbackendspringboot.controller.registration;

import com.fnb.bheki97.chatappbackendspringboot.dto.EmailSmsCodeDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
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

        return registration.persistGeekInformation(geek);
    }

    @PostMapping("/verify")
    public EmailSmsCodeDto verifyGeekInfo(@RequestBody Geek geek){

        return (EmailSmsCodeDto) verifier.verifyGeek(geek);
    }

}
