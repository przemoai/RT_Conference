package com.example.demo.controller;

import com.example.demo.model.Conference;
import com.example.demo.model.ThemeConference;
import com.example.demo.service.ConferenceService;
import com.example.demo.service.ThemeConferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ThemeConferenceController {

    private final ThemeConferenceService themeConferenceService;

    @GetMapping("/conference/detail")
    public List<ThemeConference> getConference(){
        return themeConferenceService.getConferences();
    }

}
