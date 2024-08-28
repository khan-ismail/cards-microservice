package com.zerotoismail.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourcesNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourcesNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found given input data %s : %s", resourceName, fieldName, fieldValue));
    }
}
