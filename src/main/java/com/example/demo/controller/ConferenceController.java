package com.example.demo.controller;

import com.example.demo.model.Conference;
import com.example.demo.service.ConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConferenceController {

    private final ConferenceService conferenceService;

    @GetMapping("/conferences")
    public List<Conference> getConference(){
        return conferenceService.getConferences();
    }

    @GetMapping("/conference/{id}")
    public Conference getConference(@PathVariable Long id){
        return conferenceService.getConference(id);
    }
}
