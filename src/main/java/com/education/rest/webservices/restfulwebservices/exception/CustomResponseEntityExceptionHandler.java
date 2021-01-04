package com.education.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/*
It is important to have consistent exception message, that are returned back from all the services
inside your applications or company applications.

So if you have lots of applications and each of the services return the exceptions in a different way
that is not helpful at all when finding errors and debugging those.

It's easier if you set an Standard Exception message for specific errors and that one is followed in
all the applications, for easier debugging.

This can be achieved using ResponseEntityExceptionHandler, which is a way for Spring to have
centralized exceptions across all @RequestMapping methods through @ExceptionHandler methods and
all @RestController.

This all is done with Spring Dependency Injection

More info about centralized custom exceptions in Section 3 video 24.
*/
@ControllerAdvice // let's us share the exception across all @Controllers.
@RestController // So we can provide a response back in case of exceptions
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class) // We tell string to handle all Exceptions.class that occur in the application
    public final ResponseEntity<Object> handleAllExceptions(
            Exception ex, WebRequest request){
        /*
        Whenever an exception occurs @ExceptionHandler catches it then calls this method.
        Here wa call our Standarized CustomException
         */
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class) // Handle only UserNotFoundExceptions
    public final ResponseEntity<Object> handleUserNotFoundExceptions(
            Exception ex, WebRequest request){
        /*
        Whenever an exception occurs @ExceptionHandler catches it then calls this method.
        Here wa call our Standarized CustomException
         */
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PostNotFoundException.class) // Handle only UserNotFoundExceptions
    public final ResponseEntity<Object> handlePostNotFoundExceptions(
            Exception ex, WebRequest request){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /*
    * This method overrides the handleMethodArgumentNotvalid() that spring automatically handles when trying to bing to an specific argument fails.
    * Since our USER bean already haves javax.validation.constraints @Annotations, this method is called when those annotations are triggered,
    * which means the conditions of the annotations are not valid.
    *
    * If we override the method we can throw our custom errors, since SpringBootApplication.class is doing the catching of errors.
    * */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        //getBindingResults() is getting all the details of the actual error.
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed.", ex.getBindingResult().toString());

        //We're returning our own error code, with our message exception.
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}