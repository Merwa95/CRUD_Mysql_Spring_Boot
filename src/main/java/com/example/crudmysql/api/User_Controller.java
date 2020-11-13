package com.example.crudmysql.api;

import com.example.crudmysql.model.User;
import com.example.crudmysql.service.User_logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class User_Controller {
    @Autowired
    private User_logic user_service;
    //getUsers
    @GetMapping(value="/users")
    public List<User> getUsers(){
       return user_service.getUsers();
    }
    //get one user
    @GetMapping(value="/{id}")
    public User getUser(@PathVariable("id") Long id){
        return user_service.getUser(id);
    }
    //ajouter un user
    @PostMapping
    public User addUser(@RequestBody User user) {
        return user_service.addUser(user);
    }

    //delete un user
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long id){
        user_service.delete(id);
    }

    }



