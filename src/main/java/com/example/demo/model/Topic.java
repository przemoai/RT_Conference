package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "topic")
@Getter
@Setter
@NoArgsConstructor
public class Topic {
    @Id
    private Long id;
    private String title;

//    @ManyToMany
//    private Set<Conference> conferences = new HashSet<>();

}
