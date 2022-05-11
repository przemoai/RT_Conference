package com.example.demo.service;

import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.model.ThemeConferenceParticipants;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceParticipantsRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ThemeConferenceParticipantsService {
    private final ThemeConferenceParticipantsRepository themeConferenceParticipantsRepository;
    private final ParticipantRepository participantRepository;
    private final ThemeConferenceRepository themeConferenceRepository;
    public List<ThemeConferenceParticipants> getConferencesDetails() {
        System.out.println(themeConferenceParticipantsRepository.findAll());
        return themeConferenceParticipantsRepository.findAll();
    }

    public ResponseEntity<Participant> addParticipant(Map<String,String> params) {
        Long themeConferenceId = Long.valueOf(params.get("id"));
        String participantLogin = params.get("login");

        boolean participantFound = !participantRepository.findByLogin(participantLogin).isEmpty();
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);

            Participant participant = (Participant) participantRepository.getByLogin(participantLogin);

        if(participantFound) {
            System.out.println(participant);
            try {
                ThemeConferenceParticipants _themeConferenceParticipants = themeConferenceParticipantsRepository
                        .save(new ThemeConferenceParticipants(themeConference, participant));
                return new ResponseEntity<>(null, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
