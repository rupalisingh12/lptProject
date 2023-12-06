package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.*;
import com.leanplatform.MentorshipPlatform.entities.Mentee;
import com.leanplatform.MentorshipPlatform.entities.Mentor;
import com.leanplatform.MentorshipPlatform.entities.MentorRequest;
import com.leanplatform.MentorshipPlatform.mappers.StatsMapper;
import com.leanplatform.MentorshipPlatform.repositories.MenteeRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRequestRepository;
import com.leanplatform.MentorshipPlatform.repositories.SessionRepository;
import com.leanplatform.MentorshipPlatform.services.OverallStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OverallStatsServiceImpl implements OverallStatsService {
    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorRequestRepository mentorRequestRepository;

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private MenteeRepository menteeRepository;

    @Override
    public ResponseEntity<RegisteredMentorsResponse> getAllRegisteredMentors() {
        List<MentorRequest> registeredMentors = mentorRequestRepository.findAll();
        if (registeredMentors.isEmpty()) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "No registered mentors found ",
                    null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredMentorsResponseDTO> registeredMentorsResponseDTOS = StatsMapper.convertEntityToDto(registeredMentors);
        return new ResponseEntity<>(new RegisteredMentorsResponse
                (
                        "1",
                        "Total no of registered mentors are " + registeredMentorsResponseDTOS.size(),
                        registeredMentorsResponseDTOS
                ), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<RegisteredMentorsResponse> getRegisteredMentorsCreatedPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {

        // Query mentors created within the specified date range
        List<MentorRequest> mentors = mentorRequestRepository.findByCreatedAtBetween(yesterdayStart, yesterdayEnd);

        if (mentors.isEmpty()) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "No registered mentors found ",
                    null), HttpStatus.NOT_FOUND);
        }
        // Convert entities to DTOs using StatsMapper
        List<RegisteredMentorsResponseDTO> mentorsResponseDTOS = StatsMapper.convertEntityToDto(mentors);

        // Return the response
        return new ResponseEntity<>(new RegisteredMentorsResponse(
                "1",
                "Total no of mentors registered Previous Day: " + mentorsResponseDTOS.size(),
                mentorsResponseDTOS
        ), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<RegisteredMentorsResponse> getRegisteredMentorsCreatedPreviousWeek(
            LocalDateTime fromTimestamp, LocalDateTime toTimestamp) {


        // Query mentors created within the specified date range
        List<MentorRequest> mentors = mentorRequestRepository.findByCreatedAtBetween(fromTimestamp, toTimestamp);
        if (mentors.isEmpty()) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "No registered mentors found ",
                    null), HttpStatus.NOT_FOUND);
        }

        // Convert entities to DTOs using StatsMapper
        List<RegisteredMentorsResponseDTO> mentorsResponseDTOS = StatsMapper.convertEntityToDto(mentors);

        // Return the response
        return new ResponseEntity<>(new RegisteredMentorsResponse(
                "1",
                "Total no of mentors registered in the previous week: " + mentorsResponseDTOS.size(),
                mentorsResponseDTOS
        ), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<ActiveMentorsResponse> getAllActiveMentors() {

        List<Mentor> activeMentors = mentorRepository.findAll();
        if (activeMentors.isEmpty()) {
            return new ResponseEntity<>(new ActiveMentorsResponse("0", "No active Mentors found", null), HttpStatus.NOT_FOUND);
        }
        List<ActiveMentorsResponseDTO> activeMentorsResponseDTOS = StatsMapper.convertEntityToDtoActive(activeMentors);
        return new ResponseEntity<>(new ActiveMentorsResponse(
                "1",
                "Total no of Active Mentors " + activeMentorsResponseDTOS.size(), activeMentorsResponseDTOS), HttpStatus.OK);


    }


    @Override
    public ResponseEntity<ActiveMentorsResponse> getActiveMentorsCreatedPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {

        List<Mentor> mentor = mentorRepository.findByCreatedAtBetween(yesterdayStart, yesterdayEnd);
        if (mentor.isEmpty()) {
            return new ResponseEntity<>(new ActiveMentorsResponse
                    ("0",
                            "No mentors found for previous day",
                            null), HttpStatus.NOT_FOUND);
        }
        List<ActiveMentorsResponseDTO> activeMentorsResponseDTOS = StatsMapper.convertEntityToDtoActive(mentor);
        return new ResponseEntity<>(new ActiveMentorsResponse
                ("1",
                        "Total no of Active Mentors previous day" + activeMentorsResponseDTOS.size(),
                        activeMentorsResponseDTOS), HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<ActiveMentorsResponse> getAllActiveMentorsCreatedPreviousWeek(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {

        List<Mentor> mentor = mentorRepository.findByCreatedAtBetween(yesterdayStart, yesterdayEnd);
        if (mentor.isEmpty()) {
            return new ResponseEntity<>(new ActiveMentorsResponse
                    ("0",
                            "No mentors found for previous day",
                            null), HttpStatus.NOT_FOUND);
        }
        List<ActiveMentorsResponseDTO> activeMentorsResponseDTOS = StatsMapper.convertEntityToDtoActive(mentor);
        return new ResponseEntity<>(new ActiveMentorsResponse
                ("1",
                        "Total no of Active Mentor previous week" + activeMentorsResponseDTOS.size(),
                        activeMentorsResponseDTOS), HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<SessionDoneMentorsResponse> getAlltheMentorsDoneSession() {
        List<UUID> mentorDoneSession = sessionRepository.findDistinctMentorIds();
        List<Mentor> mentorDoneSessions = mentorRepository.findMentorsByMentorIds(mentorDoneSession);
        if (mentorDoneSession.isEmpty()) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "No mentors found for ",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = StatsMapper.convertEntityToDtoSession(mentorDoneSessions);
        return new ResponseEntity<>(new SessionDoneMentorsResponse
                ("1",
                        "Total no of Active Mentor previous week" + sessionDoneMentorsResponseDTOS.size(),
                        sessionDoneMentorsResponseDTOS), HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<SessionDoneMentorsResponse> getAllTheMentorWhoHasDoneSessionPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        List<UUID> mentorDoneSession = sessionRepository.findDistinctMentorIdsBetween(yesterdayStart, yesterdayEnd);

        List<Mentor> mentorDoneSessions = mentorRepository.findMentorsByMentorIds(mentorDoneSession);
        if (mentorDoneSession.isEmpty()) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "No mentor that has done a session yesterday found for ",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = StatsMapper.convertEntityToDtoSession(mentorDoneSessions);
        return new ResponseEntity<>(new SessionDoneMentorsResponse
                ("1",
                        "Total no of Active Mentor previous week" + sessionDoneMentorsResponseDTOS.size(),
                        sessionDoneMentorsResponseDTOS), HttpStatus.CREATED);


    }
    @Override
    public ResponseEntity<RegisteredMenteeRespone>getResgisteredMentee(){
        List<Mentee>mentee=menteeRepository.findAll();
        if(mentee.isEmpty()){
            return new ResponseEntity<>(new RegisteredMenteeRespone
                    ("0", "No mentee found ",
                            null), HttpStatus.NOT_FOUND);


        }
        List<RegisteredMenteeDTOResponse>registeredMenteeDTOResponses=StatsMapper.convertEntityToDTOmentee(mentee);
        return new ResponseEntity<>(new RegisteredMenteeRespone
                ("1",
                        "Total no of Active Mentor previous week" + registeredMenteeDTOResponses.size(),
                        registeredMenteeDTOResponses), HttpStatus.OK);
    }






}




