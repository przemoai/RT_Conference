package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.model.ThemeConference;
import com.example.demo.repository.ParticipantRepository;
import com.example.demo.repository.ThemeConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ThemeConferenceService {

    private final ThemeConferenceRepository themeConferenceRepository;
    private final ParticipantRepository participantRepository;

    public List<ThemeConference> getConferences() {
        return themeConferenceRepository.findAll();
    }



}