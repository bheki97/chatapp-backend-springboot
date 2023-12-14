package com.fnb.bheki97.chatappbackendspringboot.service.authentication.jwtauth;

import com.fnb.bheki97.chatappbackendspringboot.dto.LoginDto;
import com.fnb.bheki97.chatappbackendspringboot.service.authentication.AuthManager;

public interface JwtAuthManager extends AuthManager<String, LoginDto> {


}
