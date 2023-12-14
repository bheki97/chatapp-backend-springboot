package com.fnb.bheki97.chatappbackendspringboot.dto;

import lombok.Data;

@Data
public class EmailSmsCodeDto<T,U> {

    private T smsCode;
    private U emailCode;


}
