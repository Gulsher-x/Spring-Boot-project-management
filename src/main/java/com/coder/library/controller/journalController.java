package com.coder.library.controller;

import com.coder.library.entity.journalEntity;
import com.coder.library.services.jaurnalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("journalInfo")
public class journalController {

    @Autowired
    private jaurnalServices jaurnalServices;

    @GetMapping()
    public ResponseEntity<?> getall(){
        List<journalEntity> journalEntity=jaurnalServices.getall();
        return journalEntity.isEmpty()  ? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(journalEntity,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody journalEntity journalEntity){
        journalEntity.setDate(LocalDateTime.now());
        try{
            jaurnalServices.saveEntry(journalEntity);
            return new ResponseEntity<>(journalEntity,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping("f/{id}")
    public ResponseEntity<?> findbyid(@PathVariable Long id ) {
        journalEntity journalEntity = jaurnalServices.findbyid(id);
        return journalEntity !=null ?new ResponseEntity<>(journalEntity,HttpStatus.OK):new ResponseEntity<>("Resource not Found...",HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("d/{id}")
    public ResponseEntity<?> deletebyid(@PathVariable Long id ){
        journalEntity journalEntity=jaurnalServices.findbyid(id);
        if (journalEntity !=null){
            jaurnalServices.deletebyid(id);
            return new ResponseEntity<>("Deletion complete...",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Resource not Found...",HttpStatus.NOT_FOUND);
    }
    @PutMapping("u/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody journalEntity newentity){
        journalEntity old= jaurnalServices.findbyid(id) ;
        if(old !=null){
            old.setName(newentity.getName() !=null && !newentity.getName().equals("")? newentity.getName() : old.getName());
            old.setCourse(newentity.getCourse() !=null && !newentity.getCourse().equals("")? newentity.getCourse() : old.getCourse());
            jaurnalServices.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource not Found...",HttpStatus.NOT_FOUND);
    }
}
