package com.fnb.bheki97.chatappbackendspringboot.controller.registration;

import com.fnb.bheki97.chatappbackendspringboot.dto.EmailSmsCodeDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.GeekVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.registration.GeekRegistration;
import lombok.Data;
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
    private GeekRegistration registrationService;

    @PostMapping
    public boolean completeRegistration(@RequestBody Geek geek){

        return registrationService.persistGeekInformation(geek);
    }
    @PostMapping("/verification")
    public Object verifyGeekInfo(@RequestBody Geek geek){
        Response response = new Response();
        response.setCode(verifier.verifyGeek(geek));

        return response;
    }
    @Data
    class  Response<T>{
        private T code;
    }

}

