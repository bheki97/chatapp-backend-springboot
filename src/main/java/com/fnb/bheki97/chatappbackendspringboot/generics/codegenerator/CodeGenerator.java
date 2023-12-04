package com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator;

public interface CodeGenerator<T> {


    T generateCode(int limit);
}
