package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "topic")
@Data
@NoArgsConstructor
public class Topic {
    @Id
    private Long id;
    private String title;

//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "conference_participants",
//            joinColumns = @JoinColumn(name = "conference_id"),
//            inverseJoinColumns = @JoinColumn(name = "participant_id")
//    )
//    private Set<Participant> participants = new HashSet<>();
//
//    @OneToMany(mappedBy = "topic")
//    Set<ThemeConference> details;
}
