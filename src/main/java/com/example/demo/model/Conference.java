package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
public class Conference {
    @Id
    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime finish;

    @OneToMany(mappedBy = "conference")
    Set<ConferenceTopics> topics;
}
