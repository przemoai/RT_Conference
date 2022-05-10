package com.example.demo.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Topic {
    @Id
    private Long id;
    private String title;

    @OneToMany(mappedBy = "topic")
    Set<ConferenceTopics> topics;
}
