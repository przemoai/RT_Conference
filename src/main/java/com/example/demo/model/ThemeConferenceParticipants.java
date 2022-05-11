package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeConferenceParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "themeConference_id")
    ThemeConference themeConference;

    @ManyToOne
    @JoinColumn(name = "participant_id")
    Participant participant;

    public ThemeConferenceParticipants(ThemeConference themeConference, Participant participant) {
        this.themeConference = themeConference;
        this.participant = participant;
    }
}
