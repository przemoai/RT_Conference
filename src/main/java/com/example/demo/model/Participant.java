package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Embeddable
@Data
public class Participant implements Serializable {
    private static final long serialVersionUID = -5158940195648784880L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    private Set<ThemeConference> conferences = new HashSet<>();

    public Participant(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public void addConference(ThemeConference conference) {
        this.conferences.add(conference);
    }
}
