package com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.string;

import com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.CodeGenerator;

public interface StringCodeGenerator extends CodeGenerator<String> {

    String generateCode(int limit,String chars);
}
