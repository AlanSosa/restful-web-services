package com.education.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserResource {

    /*
    @Autowired links a bean that's managed by Spring. Since UserDaoService is already
    tagged with @Component, means we are creating an Instance of it, then we are
    Autowiring the Bean to our variable.

    The Spring framework enables automatic dependency injection. In other words,
    by declaring all the bean dependencies in a Spring configuration file,
    Spring container can autowire relationships between collaborating beans. This is called
    Spring bean autowiring.
    * */
    @Autowired
    private UserDaoService service;

    //retrieve all users
    public List<User> retrieveAllUsers(){

    }

    //Retrieve User(int id)
    //Save a user(
}
