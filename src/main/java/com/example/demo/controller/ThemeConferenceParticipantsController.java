package com.example.demo.controller;

import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.model.ThemeConferenceParticipants;
import com.example.demo.service.ThemeConferenceParticipantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ThemeConferenceParticipantsController {

    private final ThemeConferenceParticipantsService themeConferenceParticipantsService;

    @GetMapping("/conference/detail")
    public List<ThemeConferenceParticipants> getConference(){
        return themeConferenceParticipantsService.getConferencesDetails();
    }

    @PostMapping("/conference/{id}/participant/{login}")
    public ResponseEntity<Participant> addParticipant(@PathVariable Map<String,String> params){
        return themeConferenceParticipantsService.addParticipant(params);
    }


}
