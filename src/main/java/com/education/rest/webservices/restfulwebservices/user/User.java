package com.education.rest.webservices.restfulwebservices.user;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class User {

    private Integer id;

    /*This annotations help us trigger the Spring exception handling.
    * When jackson maps the request to our Bean, Java.validation framework will catch an error
    * with this annotations.
    *
    * This can be catch by our CustomResponseEntityException handler, since spring is handling everything.
    * */
    @Size(min = 2)
    private String name;

    /*
    Check more javax.validation api for more information about all the validations done.
    most of them are Hibernate validators, so if you implement Hibernate stuff you will probably
    need to add this .jar to your dependency handler.

    More information about this validation handling, check chapter 3 step 16th.
    * */
    @Past
    private Date birthDate;

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
