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

    @GetMapping("/conference/detail")
    public List<ThemeConference> getConference(){
        return themeConferenceService.getConferences();
    }

    @PostMapping("/conference/{id}/participant/{name}")
    public ResponseEntity<Participant> addParticipant(@PathVariable Map<String,String> pathVarsMap){
        Long id = Long.valueOf(pathVarsMap.get("id"));
        String name = pathVarsMap.get("name");

        themeConferenceService.addParticipant(id,name);
        return null;
    }


}
