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

    public List<Participant> getParticipants(){
        return participantRepository.findAll();
    }

    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant) {

        try {
            Participant _participant = participantRepository
                    .save(new Participant(participant.getId(),participant.getName(), participant.getEmail()));
            return new ResponseEntity<>(_participant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Participant> updateParticipantEmail(@RequestBody Participant participant) {
        Optional<Participant> ParticipantData = participantRepository.findById(participant.getId());
        if (ParticipantData.isPresent()) {
            Participant _participant = ParticipantData.get();
            _participant.setEmail(participant.getEmail());
            return new ResponseEntity<>(participantRepository.save(_participant), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
