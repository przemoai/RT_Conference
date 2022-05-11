package com.example.demo.repository;

import com.example.demo.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    List<Participant> findByLogin(String login);

    Participant getByLogin(String participantLogin);

    Participant getByEmail(String participantEmail);
}
