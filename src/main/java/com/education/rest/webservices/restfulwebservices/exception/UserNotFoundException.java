package com.education.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
By using Dependency Injection we can tell Spring that when this exception is thrown
it can automatically return a 404 status code in the resource Response.

Either way, if we don't inject the @ResponseStatus Spring automatically handles the exceptions as
500 status, which are the default ones.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}