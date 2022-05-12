package com.example.demo.model;

import lombok.Data;

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
            name = "conference_participants",
            joinColumns = @JoinColumn(name = "theme_conference_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<Participant> participants = new HashSet<>();

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }
    public void removeParticipant(Participant participant){
        this.participants.remove(participant);
        participant.getConferences().remove(this);
    }


}
