package com.coder.library.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
public class users {
    @Id
    private Long id ;
    @NonNull
    @Column(unique = true)
    private String user;
    @NonNull
    private String password;
}
