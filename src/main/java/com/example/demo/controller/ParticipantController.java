package com.example.demo.controller;


import com.example.demo.model.Participant;
import com.example.demo.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @GetMapping("/participant")
    public List<Participant> getParticipants(){
        return participantService.getParticipants();
    }

    @PostMapping("/participant")
    public ResponseEntity<Participant> addParticipant(@RequestBody Participant participant){
        return participantService.addParticipant(participant);
    }

    @PutMapping("/participant")
    public ResponseEntity<Participant> updateParticipantEmail(@RequestBody Participant participant){
        return participantService.updateParticipantEmail(participant);
    }

}
