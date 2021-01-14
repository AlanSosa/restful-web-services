package com.education.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    //Method that returns "hello world"
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    //Document more of this.
    @GetMapping(path = "/hello-world-internationalized")
    //Here we're reading the "Accept-Language" header to decide the locale.
    public String helloWorldInternationalized(){
        //This is the new implementation that Spring automatically offers
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

    //UPDATE
    //This is how previously as setup before. Spring already handles this for us, so we don't need
    //to read a header to enable internationalization.
    /*public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("good.morning.message", null, locale);
    }*/
}
