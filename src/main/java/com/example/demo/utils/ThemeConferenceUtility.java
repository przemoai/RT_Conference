package com.example.demo.utils;

import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.service.ThemeConferenceService;
import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ThemeConferenceUtility {
    private final ThemeConferenceService themeConferenceService;

    public ThemeConferenceUtility(ThemeConferenceService themeConferenceService) {
        this.themeConferenceService = themeConferenceService;
    }

    @NotNull
    public static Timestamp getTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    public static String notificationContent(String participantLogin, String conferenceName, String conferenceTopic, LocalDateTime conferenceStartTime) {
        Timestamp timestamp = getTimestamp();
        String notificationContent =
                "====\nSENT " + timestamp + " TO " + participantLogin +
                        "\nSUCCESSFULLY SIGNED TO " + conferenceName
                        + " ABOUT " + conferenceTopic
                        + ". STARTS AT " + conferenceStartTime;
        return notificationContent;
    }

    public static boolean isParticipantSignedToConference(Participant participant, ThemeConference themeConference) {
        return themeConference.getParticipants().contains(participant);
    }

    public static boolean isAddingParticipantToConferenceAllowed(ThemeConference themeConference) {
        int numberOfParticipants = themeConference.getParticipants().size();
        int allowedNumberOfParticipants = 5;
        return numberOfParticipants < allowedNumberOfParticipants;
    }


}