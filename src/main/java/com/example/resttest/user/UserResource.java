package com.example.resttest.user;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {
    private final UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.findAll();
    }


    //HATEOAS
    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id){
        User user = service.findById(id);
        if (user == null)
            throw  new UserNotFoundException("id:"+id);

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(webMvcLinkBuilder.withRel("all-users"));
        return model;

    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        int userID = service.save(user).getId();
        URI uri = URI.create("/users/"+userID);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userID).toUri();
        return  ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
