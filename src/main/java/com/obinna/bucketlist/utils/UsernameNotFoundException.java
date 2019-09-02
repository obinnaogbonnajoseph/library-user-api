package com.obinna.bucketlist.utils;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameNotFoundException(String msg) {
        super(msg);
    }
}
