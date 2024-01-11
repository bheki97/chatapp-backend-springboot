package com.fnb.bheki97.chatappbackendspringboot.service.authentication;

import com.fnb.bheki97.chatappbackendspringboot.dto.AuthDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;

public interface AuthManager<U> {

    AuthDto authenticateGeek(U dto);


}
