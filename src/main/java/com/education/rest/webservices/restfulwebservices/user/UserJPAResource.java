package com.education.rest.webservices.restfulwebservices.user;

import com.education.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.education.rest.webservices.restfulwebservices.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

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

    @Autowired
    private UserRepository userRepository;

    //retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    //Retrieve User(int id)
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retriveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User with id = " + id + " doesn't exist");
        }

        //This is part of HATEOAS
        //Create the type or resource to return
        EntityModel<User> resource = new EntityModel<User>(user.get());
        //Add the links we want to add in the response parameter "all-users".
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        //This is how the parameter will appear in JSON response
        resource.add(linkTo.withRel("all-users"));

        /*
        * Example response:
        * EntityModel>
        <id>1</id>
        <name>Adam</name>
        <birthDate>2021-01-14T04:06:04.106+00:00</birthDate>
        <links>
        <rel>all-users</rel>
        <href>http://localhost:8080/users</href> <-HATEOAS STUFF
        </links>
        </EntityModel>
        * */
        return resource;
    }

    /*
    Jackson automatically maps the json sent in the body to the User bean by validating the name of properties.
    If you send a property that is not declared in the User bean, jackson will simply ignore it.

    for example:
    {
    "name": "Texting name 1",
    "anotherColumn":"Something here"
    }

    'anotherColumn' value will be ignored and jackson will map the bean like this:

    id: null
    name: "Texting name 1"
    date : null

     */
    //Input = details of new User
    //Output = Created Status & return the created URI (/users/{new id})
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//Returns the current URI '/users'
                .path("/{id}") //Then we append the id which is taken from the next method.
                .buildAndExpand(savedUser.getId()) //This one gets the ID from the User bean.
                .toUri(); //Parse the whole thing to URI

        /*The location will be in the response headers like this:
        location → http://localhost:8080/users/5
         */
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
        return new ResponseEntity("Succesfully deleted user " + id, HttpStatus.OK);
    }

    //retrieve all users
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        //System.out.println(user.toString());
        if(!user.isPresent()){
            throw new UserNotFoundException("User with id = " + id + " doesn't exist");
        }
        return user.get().getPosts();
    }
}
