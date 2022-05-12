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


    public ResponseEntity<Participant> addParticipant(Map<String, String> params) {
        Long themeConferenceId = Long.valueOf(params.get("id"));
        String participantLogin = params.get("login");
        Participant participant = participantRepository.getByLogin(participantLogin);
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);

        try {
            if (isAddingParticipantToConferenceAllowed(themeConference)) {
                themeConference.addParticipant(participant);
                themeConferenceRepository.save(themeConference);
            } else {
                System.out.println("MAX SLOTS");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
        return new ResponseEntity<>(null, HttpStatus.OK);
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