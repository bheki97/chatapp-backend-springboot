package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;

public interface VerificationCodeSender {
    void sendGeekVerificationCode(Geek geek);

}
