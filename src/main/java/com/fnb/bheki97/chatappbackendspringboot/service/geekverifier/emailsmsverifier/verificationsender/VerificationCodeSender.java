package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.verificationsender;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;

public interface VerificationCodeSender<T> {
    T sendGeekVerificationCode(Geek geek);

}
