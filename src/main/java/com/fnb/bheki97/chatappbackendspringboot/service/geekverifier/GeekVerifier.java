package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;

public interface GeekVerifier<T> {
    T verifyGeek(Geek geek);
}
