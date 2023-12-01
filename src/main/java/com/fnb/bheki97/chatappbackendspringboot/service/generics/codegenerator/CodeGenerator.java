package com.fnb.bheki97.chatappbackendspringboot.service.generics.codegenerator;

public interface CodeGenerator<T> {


    T generateCode(int limit);
}
