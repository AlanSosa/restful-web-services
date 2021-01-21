package com.education.rest.webservices.restfulwebservices.post;

import com.education.rest.webservices.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    /*
    We mark it as lazy. In case the Post is created in memory
    so making it lazy, the USER is not brought from the DB unless
    we call post.getUser()

    Also is marked as ManyToOne since an User can have MANY post related
    to him/her.

    @JsonIgnore is used when the list is parsed to Json by Jackson
    Because User contains POSTS lists, then when reading the POST object
    will read the USER, then it will have a recursive loop that will parse
    USER -> POST -> USER -> POST
    So by annotating the JsonIgnore in the User variable, we're ignoring it in the
    JSON.
    */
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String title;
    private String content;

    public Post(){

    }

    public Post(int id, User user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + user.toString() +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
