package com.example.demo.service;

import com.example.demo.model.Participant;
import com.example.demo.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public ResponseEntity addParticipant(@RequestBody Participant participant) {


        if (isParticipantExist(participant)) {
            try {
                Participant newParticipant = participantRepository
                        .save(new Participant(participant.getLogin(), participant.getEmail()));
                return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("LOGIN JEST JUZ ZAJETY");
        }
    }

    private boolean isParticipantExist(Participant participant) {
        boolean exists = participantRepository.findByLogin(participant.getLogin()).isEmpty();
        return exists;
    }

    public ResponseEntity updateParticipantEmail(@RequestBody Participant participant) {
        Optional<Participant> ParticipantData = participantRepository.findById(participant.getId());

        if (ParticipantData.isPresent()) {
            Participant newParticipant = ParticipantData.get();
            newParticipant.setEmail(participant.getEmail());
            return new ResponseEntity<>(participantRepository.save(newParticipant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
