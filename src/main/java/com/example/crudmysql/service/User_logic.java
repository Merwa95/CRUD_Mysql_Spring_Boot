package com.example.crudmysql.service;

import com.example.crudmysql.dao.UserRepository;
import com.example.crudmysql.exception.ResourceNotFoundException;
import com.example.crudmysql.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class User_logic {
    @Autowired
    private UserRepository userDao;

    public List<User> getUsers(){
        return userDao.findAll();
    }
    public User getUser(Long id){
    /*    Optional<User> optionalUser=userDao.findById(id);
        if(optionalUser.isEmpty())
            throw new ResourceNotFoundException("user is not Found");
        return optionalUser.get();*/
        return  userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("user  not found"));
    }

    public User addUser(User user){
        return userDao.save(user);
    }

    //public User updateUser(U)

    public void delete(long id){
        userDao.deleteById(id);
    }
}
