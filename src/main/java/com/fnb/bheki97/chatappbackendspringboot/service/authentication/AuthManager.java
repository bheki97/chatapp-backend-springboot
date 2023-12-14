package com.fnb.bheki97.chatappbackendspringboot.service.authentication;

import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;

public interface AuthManager<T,U> {

    T authenticateGeek(U dto);


}
