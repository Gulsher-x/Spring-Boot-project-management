package com.coder.library.controller;

import com.coder.library.entity.users;
import com.coder.library.services.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class userController {
    @Autowired
    private userServices userServices;
    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<users> users=userServices.getUsers();
        return users.isEmpty()?new ResponseEntity<>("Resource not Found...",HttpStatus.NOT_FOUND):new ResponseEntity<>(users,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> saveAllUser(@RequestBody users users){
        try {
            users user=userServices.saveUser(users);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("check Request Body may be column have duplicate Value...",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody users newUserData,@PathVariable String username){
        users usersDb=userServices.findUser(username);
        if(usersDb !=null ){
            usersDb.setUsername(newUserData.getUsername());
            usersDb.setPassword(newUserData.getPassword());
            userServices.saveUser(usersDb);
            return new ResponseEntity<>(usersDb,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
