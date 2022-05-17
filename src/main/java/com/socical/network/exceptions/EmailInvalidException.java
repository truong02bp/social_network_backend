package com.socical.network.exceptions;

import org.springframework.http.HttpStatus;

public class EmailInvalidException extends BusinessException {

    public EmailInvalidException(String email) {
        this.message = email + " is invalid";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
