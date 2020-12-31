package com.education.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
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
    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //Retrieve User(int id)
    @GetMapping("/users/{id}")
    public User retriveUser(@PathVariable int id){
        return service.findOne(id);
    }
    
    /*
    Jackson automatically maps the json sent in the body to the User bean by validating the name of properties.
    If you send a property that is not declared in the User bean, jackson will simply ignore it.

    for example:
    {
    "name": "Texting name 1",
    "anotherColumn":"Something here"
    }

    anotherColumn value will be ignored and jackson will map the bean like this:

    id: null
    name: "Texting name 1"
    date : null

     */
    //Input = details of new User
    //Output = Created Status & return the created URI (/users/{new id})
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = service.save(user);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//Returns the current URI '/users'
                .path("/{id}") //Then we append the id which is taken from the next method.
                .buildAndExpand(savedUser.getId()) //This one gets the ID from the User bean.
                .toUri(); //Parse the whole thing to URI

        /*The location will be in the response headers like this:
        location → http://localhost:8080/users/5
         */
        return ResponseEntity.created(location).build();
    }
}
