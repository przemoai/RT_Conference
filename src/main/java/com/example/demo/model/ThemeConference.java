package com.example.demo.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class ThemeConference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    Conference conference;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "Theme_Conference_Participants",
            joinColumns = @JoinColumn(name = "themeConference_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants = new HashSet<>();



}
