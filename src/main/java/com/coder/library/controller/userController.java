package com.coder.library.controller;

import com.coder.library.entity.users;
import com.coder.library.services.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
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
    @GetMapping("find/{id}")
    public ResponseEntity<?> findUser(@PathVariable Long id){
        users user =userServices.findUser(id);
        return user != null ?new ResponseEntity<>(user, HttpStatus.FOUND): new ResponseEntity<>("Resource not Found ",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable Long id){
        users user =userServices.findUser(id);
        if(user != null){
            userServices.deleteUser(id);
            return new ResponseEntity<>("Deletion Complete...",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("No Resource Found for Deletion...",HttpStatus.NOT_FOUND);
    }
}
