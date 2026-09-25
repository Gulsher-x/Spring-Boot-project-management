package com.coder.library.services;

import com.coder.library.entity.journalEntity;
import com.coder.library.repository.jaurnalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class jaurnalServices {
    @Autowired
    private jaurnalRepo jaurnalRepo;
    public journalEntity saveEntry(journalEntity journalEntity){
        return jaurnalRepo.save(journalEntity);
    }

    public List<journalEntity> getall(){
        return jaurnalRepo.findAll();
    }

    public journalEntity findbyid(Long id ){
        return jaurnalRepo.findById(id).orElse(null);
    }
    public void deletebyid(Long id ){
         jaurnalRepo.deleteById(id);
    }
}
