package com.coder.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class journalEntity {
    @Id
    private Long id;
    private String name;
    private String course;
    private LocalDateTime date;

    }
