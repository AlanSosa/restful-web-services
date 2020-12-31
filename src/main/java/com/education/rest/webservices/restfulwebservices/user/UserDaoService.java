package com.education.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
We assign it as @Component so Spring can Manage the bean.
@Component is the most generic Spring annotation. A Java class decorated with @Component is found
during classpath scanning and registered in the context as a Spring bean. @Service, @Repository,
and @Controller are specializations of @Component, which are used for more specific cases.

@ComponentScan ensures that the classes decorated with @Component are found and registered as
Spring beans. @ComponentScan is automatically included with @SpringBootApplication.
 */
@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static{
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Alan", new Date()));
        users.add(new User(3, "Miauricio", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
