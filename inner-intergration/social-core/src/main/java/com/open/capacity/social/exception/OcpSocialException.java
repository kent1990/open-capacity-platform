package com.open.capacity.social.exception;

import javax.security.sasl.AuthenticationException;

public class OcpSocialException extends RuntimeException {

    public OcpSocialException() {
        super();
    }

    public OcpSocialException(String detail) {
        super(detail);
    }

    public OcpSocialException(String detail, Throwable ex) {
        super(detail, ex);
    }
}
