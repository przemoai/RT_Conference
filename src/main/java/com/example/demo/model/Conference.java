package com.example.demo.model;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "conference")
@NoArgsConstructor
@Data
public class Conference {
    @Id
    private Long id;
    private String title;
    private LocalDateTime start;
    private LocalDateTime finish;

//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "conference_topics",
//            joinColumns = @JoinColumn(name = "conference_id"),
//            inverseJoinColumns = @JoinColumn(name = "topic_id")
//    )
//    private Set<Topic> topics = new HashSet<>();
//    @OneToMany(mappedBy = "conference")
//    Set<ThemeConference> details;


}
