package com.example.demo.service;

import com.example.demo.exceptions.NotFound;
import com.example.demo.model.Conference;
import com.example.demo.model.ThemeConference;
import com.example.demo.repository.ConferenceRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeConferenceService {

    private final ThemeConferenceRepository themeConferenceRepository;

    public List<ThemeConference> getConferences(){
        System.out.println(themeConferenceRepository.findAll().toString());
        return themeConferenceRepository.findAll();
    }

}
