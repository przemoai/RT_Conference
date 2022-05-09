package com.example.demo.model;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
public class Topic {
    @Id
    private Long id;
    private String title;

    @ManyToMany

    Set<Conference> conferences;
}
