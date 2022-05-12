package com.example.demo.service;

import com.example.demo.model.Participant;
import com.example.demo.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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

        if (!isParticipantExist(participant)) {
            try {
                Participant newParticipant = participantRepository
                        .save(new Participant(participant.getLogin(), participant.getEmail()));
                return new ResponseEntity<>(newParticipant, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("LOGIN IS IN USE");
        }
    }

    private boolean isParticipantExist(@NotNull Participant participant) {
        boolean exists = !participantRepository.findByLogin(participant.getLogin()).isEmpty();
        return exists;
    }

    public ResponseEntity updateParticipantEmail(@RequestBody Participant participant) {

        if (isParticipantExist(participant)) {
            Participant updatedParticipant = getParticipant(participant);
            return new ResponseEntity<>(participantRepository.save(updatedParticipant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @NotNull
    private Participant getParticipant(Participant participant) {
        Optional<Participant> participantData = participantRepository.findById(participant.getId());
        Participant updatedParticipant = participantData.get();
        updatedParticipant.setEmail(participant.getEmail());
        return updatedParticipant;
    }

}
