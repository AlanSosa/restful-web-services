package com.education.rest.webservices.restfulwebservices.user;

import com.education.rest.webservices.restfulwebservices.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "User details")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    /*This annotations help us trigger the Spring exception handling.
    * When jackson maps the request to our Bean, Java.validation framework will catch an error
    * with this annotations.
    *
    * This can be catch by our CustomResponseEntityException handler, since spring is handling everything.
    * */
    @Size(min = 2, message = "Name should have at least 2 charactes")
    @ApiModelProperty(notes = "Name should at least have 2 characters")
    private String name;

    /*
    Check more javax.validation api for more information about all the validations done.
    most of them are Hibernate validators, so if you implement Hibernate stuff you will probably
    need to add this .jar to your dependency handler.

    More information about this validation handling, check chapter 3 step 16th.
    * */
    @Past
    @ApiModelProperty(notes = "Birth day should be in the past")
    private Date birthDate;

    /*
    An user can have many post. So that's why we mapped as
    ONE = user To MANY = posts
    Then in mappedBy parameter we tell JPA what field is that has the relationship in
    POST.
    * */
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(){

    }

    public User(Integer id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
                ", posts=" + posts +
                '}';
    }
}
