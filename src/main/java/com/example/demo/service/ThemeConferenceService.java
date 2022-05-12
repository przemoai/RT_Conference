package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThemeConferenceService {
    private final ThemeConferenceRepository themeConferenceRepository;
    private final ParticipantRepository participantRepository;

    public List<ThemeConference> getConferences() {
        return themeConferenceRepository.findAll();
    }
    public ResponseEntity addParticipantToConference(Map<String, String> params) {
        Long themeConferenceId = Long.valueOf(params.get("id"));
        String participantLogin = params.get("login");
        Participant participant = participantRepository.getByLogin(participantLogin);
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);


        try {
            if (isAddingParticipantToConferenceAllowed(themeConference)) {
                themeConference.addParticipant(participant);
                themeConferenceRepository.save(themeConference);
            } else {
                //return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
                return ResponseEntity.status(HttpStatus.CONFLICT).body("LIST OF PARTICIPANTS IS FULL, TRY ANOTHER CONFERENCE");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
            }
        return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT ADDED SUCCESSFULLY");
    }

    public ResponseEntity removeParticipantFromConference(Map<String, String> params){
        Long themeConferenceId = Long.valueOf(params.get("id"));
        String participantLogin = params.get("login");
        Participant participant = participantRepository.getByLogin(participantLogin);
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);


        if(isParticipantSignedToConference(participant, themeConference)){
            try{
                themeConference.removeParticipant(participant);
                themeConferenceRepository.save(themeConference);
                return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT REMOVED SUCCESSFULLY");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
            }
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("PARTICIPANT IS NOT SIGNED TO THIS CONFERENCE");
        }


    }

    private boolean isParticipantSignedToConference(Participant participant, ThemeConference themeConference) {
        return themeConference.getParticipants().contains(participant);
    }

    private boolean isAddingParticipantToConferenceAllowed(ThemeConference themeConference) {
        int numberOfParticipants = themeConference.getParticipants().size();
        int allowedNumberOfParticipants = 5;
        return numberOfParticipants < allowedNumberOfParticipants;
    }

    public ThemeConference getConference(Long id) {
        return themeConferenceRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}