package com.example.demo.utils;

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
                        + " CONFERENCE ABOUT " + conferenceTopic
                        + " STARTS AT " + conferenceStartTime;
        return notificationContent;
    }

    public static String generateNotificationContent(String participantLogin, String conferenceName, String conferenceTopic, LocalDateTime conferenceStartTime) {
        return notificationContent(participantLogin, conferenceName, conferenceTopic, conferenceStartTime);
    }
}