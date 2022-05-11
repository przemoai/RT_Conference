package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Conference;
import com.example.demo.repository.ConferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public List<Conference> getConferences(){
        System.out.println(conferenceRepository.findAll());
        return conferenceRepository.findAll();
    }

    public Conference getConference(Long id) {
        return conferenceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
