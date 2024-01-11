package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

@Data
public class TokenAuthDto extends AuthDto{
    private String authToken;

}
