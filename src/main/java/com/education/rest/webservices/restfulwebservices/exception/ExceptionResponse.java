package com.education.rest.webservices.restfulwebservices.exception;

import java.util.Date;

/*
It is important to have consistent exception message, that are returned back from all the services
inside your applications or company applications.

So if you have lots of applications and each of the services return the exceptions in a different way
that is not helpful at all when finding errors and debugging those.

It's easier if you set an Standard Exception message for specific errors and that one is followed in
all the applications, for easier debugging.

This Class will help us make an Standard of our Exceptions.
 */
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
