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
//        System.out.println(themeConferenceRepository.findAll().toString());
        return themeConferenceRepository.findAll();
    }

    public void addParticipant(Long themeConferenceId, String participantLogin) {
        ThemeConference themeConference = themeConferenceRepository.getById(themeConferenceId);
        Participant participant = participantRepository.getByLogin(participantLogin);

       themeConference.addParticipant(themeConference.getParticipants(),participant);
       System.out.println(themeConference);

    }



//    public void addParticipant(Long themeConferenceId, Participant participantRequest) {
//        Participant participant = themeConferenceRepository.findById(themeConferenceId).map(tutorial -> {
//            long tagId = participantRequest.getId();
//            // tag is existed
//            if (tagId != 0L) {
//                Participant _participant = participantRepository.findById(tagId)
//                        .orElseThrow(NotFoundException::new);
//                tutorial.addParticipant(_participant);
//                themeConferenceRepository.save(tutorial);
//                return _participant;
//            }
//
//        }
//        return;
//    }
}