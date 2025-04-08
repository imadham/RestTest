package com.example.resttest.user.jpa;

import com.example.resttest.user.Post;
import com.example.resttest.user.User;
import com.example.resttest.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResourceJpa {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserResourceJpa(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //HATEOAS
    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw  new UserNotFoundException("id:"+id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(webMvcLinkBuilder.withRel("all-users"));
        return model;
    }


    @GetMapping("/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Integer id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw  new UserNotFoundException("id:"+id);
        return user.getPosts();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        int userID = userRepository.save(user).getId();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userID).toUri();
        return  ResponseEntity.created(location).build();

    }


    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new UserNotFoundException("id:"+id);

        post.setOwner(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
