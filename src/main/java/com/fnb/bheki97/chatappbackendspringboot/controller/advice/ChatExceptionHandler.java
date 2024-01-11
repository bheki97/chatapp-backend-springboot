package com.fnb.bheki97.chatappbackendspringboot.controller.advice;

import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ChatAppException.class, UsernameNotFoundException.class})
    public String handleChatAppExceptions(Exception exc){
        return exc.getMessage();
    }
}
