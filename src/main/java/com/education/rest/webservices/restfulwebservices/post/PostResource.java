package com.education.rest.webservices.restfulwebservices.post;

import com.education.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.education.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostResource {

    @Autowired
    private PostDaoService service;

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id){
        List<Post> posts = service.findAllUserPost(id);
        if(posts == null){
            throw new UserNotFoundException("Posts for user : " + id + " not found");
        }
        return service.findAllUserPost(id);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post){
        post.getUser().setId(id);
        Post savedPost = service.save(post);

        if(savedPost == null){
            throw new UserNotFoundException("Can't create post for non existent user :" + id);
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

     return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public Post retrievePostDetails(@PathVariable int userId, @PathVariable int postId){
        Post post = service.findPost(userId, postId);
        if(post == null){
            throw new PostNotFoundException("Post id : " + postId + " for user : " + userId + " doesn't exists");
        }
        return post;
    }
}
