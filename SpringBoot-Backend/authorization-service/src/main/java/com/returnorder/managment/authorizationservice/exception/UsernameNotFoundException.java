package com.returnorder.managment.authorizationservice.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {
    public UsernameNotFoundException(String msg) {
        super(msg);
    }
}
