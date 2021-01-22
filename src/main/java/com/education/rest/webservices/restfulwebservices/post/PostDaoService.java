package com.education.rest.webservices.restfulwebservices.post;

import com.education.rest.webservices.restfulwebservices.user.User;
import com.education.rest.webservices.restfulwebservices.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDaoService {
    private static List<Post> posts = new ArrayList<>();
    private static int postCount = 3;

    @Autowired
    UserDaoService userService;


    /*
    commented out this parts since we're doing relations using JPA in Post Model
    static{
        posts.add(new Post(1, 1,"Adam's Post", "I'm adam and this is my post"));
        posts.add(new Post(2, 2,"Alan's Post", "I'm alan and this is my post"));
        posts.add(new Post(3, 3,"Miauricio's Post", "I'm miauricio and this is my post"));
    }*/

    public Post save(Post post){
        List<User> usersList = userService.getUsers();
        for(User user : usersList){
            if( post.getUser().getId() == user.getId()){
                post.setId(++postCount);
                posts.add(post);
                System.out.println("Saved post : " + post.toString());
                return post;
            }
        }
        return null;
    }

    public List<Post> findAllUserPost(int id){
        List<Post> userPosts = new ArrayList<>();

        for(Post post : posts){
            if(post.getUser().getId() == id ){
                userPosts.add( post );
            }
        }

        if(userPosts.isEmpty()){
            return null;
        }else{
          return userPosts;
        }
    }

    public Post findPost(int userId, int postid){
        for(Post post : posts){
            if(post.getUser().getId() == userId && post.getId()== postid){
                return post;
            }
        }
        return null;
    }

}
