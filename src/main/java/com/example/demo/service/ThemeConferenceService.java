package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import com.example.demo.utils.ThemeConferenceUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

import static com.example.demo.utils.ThemeConferenceUtility.isAddingParticipantToConferenceAllowed;
import static com.example.demo.utils.ThemeConferenceUtility.isParticipantSignedToConference;

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
                if (!isParticipantSignedToConference(participant, themeConference)) {
                    themeConference.addParticipant(participant);
                    themeConferenceRepository.save(themeConference);
                    sendSigningNotification(themeConference, participant);
                    return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT ADDED SUCCESSFULLY");
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT IS ALREADY SIGNED TO CONFERENCE");
                }
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("LIST OF PARTICIPANTS IS FULL, TRY ANOTHER CONFERENCE");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
        }

    }


    public void sendSigningNotification(ThemeConference themeConference, Participant participant) {
        String pathToFile = "notifications.txt";
        try (FileWriter fw = new FileWriter(pathToFile, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
            out.println(ThemeConferenceUtility.notificationContent(participant.getLogin(), themeConference.getConference().getTitle(), themeConference.getTopic().getTitle(), themeConference.getConference().getStart()));
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public ResponseEntity removeParticipantFromConference(Map<String, String> params) {
        Long themeConferenceId = Long.valueOf(params.get("id"));
        String participantLogin = params.get("login");
        Participant participant = participantRepository.getByLogin(participantLogin);
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);


        try {
            if (isParticipantSignedToConference(participant, themeConference)) {
                themeConference.removeParticipant(participant);
                themeConferenceRepository.save(themeConference);
                return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT REMOVED SUCCESSFULLY");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PARTICIPANT IS NOT SIGNED TO THIS CONFERENCE");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
        }
    }

    public ThemeConference getConference(Long id) {
        return themeConferenceRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}