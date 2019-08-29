package com.obinna.bucketlist.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotCreatedException extends Exception {

    public NotCreatedException() {
        super();
    }

    public NotCreatedException(String message) {
        super(message);
    }
}
