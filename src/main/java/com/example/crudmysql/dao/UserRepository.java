package com.example.crudmysql.dao;

import com.example.crudmysql.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    List<User> findAll();
}
