package com.example.demo.controller;

import com.example.demo.model.Conference;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.service.ConferenceService;
import com.example.demo.service.ThemeConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ThemeConferenceController {

    private final ThemeConferenceService themeConferenceService;

    @GetMapping("/conference/details")
    public List<ThemeConference> getConference(){
        return themeConferenceService.getConferences();
    }

    @GetMapping("/conference/details/{id}")
    public ThemeConference getConference(@PathVariable Long id){
        return themeConferenceService.getConference(id);
    }




    @PostMapping("/conference/{id}/participant/{login}")
    public ResponseEntity<Participant> addParticipant(@PathVariable Map<String,String> params){
        return themeConferenceService.addParticipant(params);
    }




}
