package com.example.jpah2.demo.Exception;

import java.text.MessageFormat;

public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException(final Long id){
        super(MessageFormat.format("no se encontro el id: {0}",id));
    }
}
