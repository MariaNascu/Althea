package com.fortech.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Filed is mandatory")
    public class FiledIsMandatoryException extends RuntimeException {

        public FiledIsMandatoryException(String msg) {
            super(msg);
        }
}
