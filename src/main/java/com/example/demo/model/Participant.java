package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


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



    public Participant(String login, String email) {
        this.login = login;
        this.email = email;
    }
}
