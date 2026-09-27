package com.coder.library.services;

import com.coder.library.entity.users;
import com.coder.library.repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userServices {
    @Autowired
    private userRepo userRepo;
    public List<users> getUsers(){
        return userRepo.findAll();
    }
    public users saveUser(users users){
        return userRepo.save(users);
    }
    public users findUser(String user){
        return userRepo.findByUsername(user);
    }
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

}
