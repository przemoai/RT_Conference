package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
                sendSigningNotification(themeConference, participant);
                return ResponseEntity.status(HttpStatus.OK).body("PARTICIPANT ADDED SUCCESSFULLY");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("LIST OF PARTICIPANTS IS FULL, TRY ANOTHER CONFERENCE");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e);
        }

    }

    String notificationContent(String participantLogin, String conferenceName, String conferenceTopic, LocalDateTime conferenceStartTime) {
        Timestamp timestamp = getTimestamp();
        String notificationContent =
                "====\nSENT " + timestamp + " TO " + participantLogin +
                        "\nSUCCESSFULLY SIGNED TO " + conferenceName
                        + " CONFERENCE ABOUT " + conferenceTopic
                        + " STARTS AT " + conferenceStartTime;
        return notificationContent;
    }
    @NotNull
    private Timestamp getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public void sendSigningNotification(ThemeConference themeConference, Participant participant) {
        String pathToFile = "notifications.txt";
        try (FileWriter fw = new FileWriter(pathToFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(notificationContent(
                    participant.getLogin(),
                    themeConference.getConference().getTitle(),
                    themeConference.getTopic().getTitle(),
                    themeConference.getConference().getStart()
            ));
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
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