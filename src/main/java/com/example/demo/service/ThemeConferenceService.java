package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Conference;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.model.ThemeConferenceParticipants;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        themeConference.addParticipant(participant);

        try{
            themeConferenceRepository.save(themeConference);

        }catch (Exception e){

        }

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ThemeConference getConference(Long id) {
        return themeConferenceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}