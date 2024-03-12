package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.*;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Mentee;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.Mentor;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;
import com.leanplatform.MentorshipPlatform.mappers.OverallStatsMapper.StatsMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityFeatureRepository.AvailabilityRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.MentorRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.MentorRequestRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.MenteeRepository;
import com.leanplatform.MentorshipPlatform.repositories.MultifunctionalRepository.SessionRepository;
import com.leanplatform.MentorshipPlatform.services.OverallStatsFeatureService.OverallStatsService;
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
    @Autowired
    private AvailabilityRepository availabilityRepository;

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
                    "No registered mentors found for previpus day",
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
                    "No registered mentors found for previous week ",
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
                            "No active mentors found for previous day",
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
                            "No active mentors found for previous week",
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
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsBeforeCurrentTime();
        List<UUID> mentorIdsList = sessionRepository.findMentorIdsByAvailabilityIds(availabilityList);
        List<Mentor> mentorDoneSession = mentorRepository.findDistinctMentorsByMentorIds(mentorIdsList);
        if (mentorDoneSession.isEmpty()) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "No mentors found  ",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = StatsMapper.convertEntityToDtoSession(mentorDoneSession);
        return new ResponseEntity<>(new SessionDoneMentorsResponse
                ("1",
                        "Total no of Mentor who has done session" + sessionDoneMentorsResponseDTOS.size(),
                        sessionDoneMentorsResponseDTOS), HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<SessionDoneMentorsResponse> getAllTheMentorWhoHasDoneSessionPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsYesterday();
        List<UUID> mentorIdsList = sessionRepository.findMentorIdsByAvailabilityIds(availabilityList);
        List<Mentor> mentorDoneSession = mentorRepository.findDistinctMentorsByMentorIds(mentorIdsList);
        if (mentorDoneSession.isEmpty()) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "No mentor  found ",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = StatsMapper.convertEntityToDtoSession(mentorDoneSession);
        return new ResponseEntity<>(new SessionDoneMentorsResponse
                ("1",
                        "Total no of mentor who has done session previous day" + sessionDoneMentorsResponseDTOS.size(),
                        sessionDoneMentorsResponseDTOS), HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<SessionDoneMentorsResponse> getAllTheMentorwhoDidSessionPreviousWeek(LocalDateTime lastWeekStart, LocalDateTime lastWeekEnd) {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsInPreviousWeek();
        List<UUID> mentorIdsList = sessionRepository.findMentorIdsByAvailabilityIds(availabilityList);
        List<Mentor> mentorDoneSession = mentorRepository.findDistinctMentorsByMentorIds(mentorIdsList);
        if (mentorDoneSession.isEmpty()) {
            return new ResponseEntity<>(
                    new SessionDoneMentorsResponse(
                            "0",
                            "No mentor found ",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredDoneMentorsResponseDTO> sessionDoneMentorsResponseDTOS = StatsMapper.convertEntityToDtoSession(mentorDoneSession);
        return new ResponseEntity<>(new SessionDoneMentorsResponse
                ("1",
                        "Total no of mentors who has done session previous week" + sessionDoneMentorsResponseDTOS.size(),
                        sessionDoneMentorsResponseDTOS), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<RegisteredMenteeRespone> getResgisteredMentee() {
        List<Mentee> mentee = menteeRepository.findAll();
        if (mentee.isEmpty()) {
            return new ResponseEntity<>(new RegisteredMenteeRespone
                    ("0", "No mentee found ",
                            null), HttpStatus.NOT_FOUND);


        }
        List<RegisteredMenteeDTOResponse> registeredMenteeDTOResponses = StatsMapper.convertEntityToDTOmentee(mentee);
        return new ResponseEntity<>(new RegisteredMenteeRespone
                ("1",
                        "Total no of registered mentee" + registeredMenteeDTOResponses.size(),
                        registeredMenteeDTOResponses), HttpStatus.OK);
    }

    public ResponseEntity<RegisteredMenteeRespone> getMenteeWhoRegisteredPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        List<Mentee> mentee = menteeRepository.findByCreatedAtBetween(yesterdayStart, yesterdayEnd);
        if (mentee.isEmpty()) {
            return new ResponseEntity<>(
                    new RegisteredMenteeRespone(
                            "0",
                            "No mentee has done a registration previous day",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredMenteeDTOResponse> registeredMenteeDTOResponses = StatsMapper.convertEntityToDTOmentee(mentee);
        return new ResponseEntity<>(new RegisteredMenteeRespone
                ("1",
                        "Total no of mentee who has done registration previous day" + registeredMenteeDTOResponses.size(),
                        registeredMenteeDTOResponses), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegisteredMenteeRespone> getAllTheMenteWhoRegisteredPreviousWeek(LocalDateTime lastWeekStart, LocalDateTime lastWeekEnd) {
        List<Mentee> mentee = menteeRepository.findByCreatedAtBetween(lastWeekStart, lastWeekEnd);
        if (mentee.isEmpty()) {
            return new ResponseEntity<>(
                    new RegisteredMenteeRespone(
                            "0",
                            "No mentee has done a registration previous Week",
                            null), HttpStatus.NOT_FOUND);
        }
        List<RegisteredMenteeDTOResponse> registeredMenteeDTOResponses = StatsMapper.convertEntityToDTOmentee(mentee);
        return new ResponseEntity<>(new RegisteredMenteeRespone
                ("1",
                        "Total no of mentee who has done a registration previous week" + registeredMenteeDTOResponses.size(),
                        registeredMenteeDTOResponses), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<SessionDoneMenteeResponse> getAllthementeeWhoHasDoneSession() {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsBeforeCurrentTime();
        List<UUID> menteeIdsList = sessionRepository.findMenteeIdsByAvailabilityIds(availabilityList);
        List<Mentee> mentees = menteeRepository.findDistinctMenteesByMenteeIds(menteeIdsList);
        if (menteeIdsList.isEmpty()) {
            return new ResponseEntity<>(
                    new SessionDoneMenteeResponse(
                            "0",
                            "No mentee has done session previous day",
                            null), HttpStatus.NOT_FOUND);
        }
        List<SessionDoneMenteeResponseDTO> sessionDoneMenteeResponseDTOS = StatsMapper.convertEntityToDTOmenteeSessionDone(mentees);
        return new ResponseEntity<>(new SessionDoneMenteeResponse
                ("1",
                        "Total no of mentee who has done session previous day" + sessionDoneMenteeResponseDTOS.size(),
                        sessionDoneMenteeResponseDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SessionDoneMenteeResponse> getMenteeWhoDoneSessionPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd) {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsYesterday();
        List<UUID> menteeIdsList = sessionRepository.findMenteeIdsByAvailabilityIds(availabilityList);
        List<Mentee> mentees = menteeRepository.findMenteesByMenteeIds(menteeIdsList);
        if (mentees.isEmpty()) {
            return new ResponseEntity<>(
                    new SessionDoneMenteeResponse(
                            "0",
                            "No mentee has done a session previous day",
                            null), HttpStatus.NOT_FOUND);
        }
        List<SessionDoneMenteeResponseDTO> sessionDoneMenteeResponseDTOS = StatsMapper.convertEntityToDTOmenteeSessionDone(mentees);
        return new ResponseEntity<>(new SessionDoneMenteeResponse
                ("1",
                        "Total no of mentee  that has done session previous day" + sessionDoneMenteeResponseDTOS.size(),
                        sessionDoneMenteeResponseDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SessionDoneMenteeResponse> getMenteeWhoDoneSessionPreviousWeek(LocalDateTime lastWeekStart, LocalDateTime lastWeekEnd) {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsInPreviousWeek();
        List<UUID> menteeIdsList = sessionRepository.findMenteeIdsByAvailabilityIds(availabilityList);
        List<Mentee> mentees = menteeRepository.findMenteesByMenteeIds(menteeIdsList);
        if (mentees.isEmpty()) {
            return new ResponseEntity<>(
                    new SessionDoneMenteeResponse(
                            "0",
                            "No mentee has done a session previous Week",
                            null), HttpStatus.NOT_FOUND);
        }
        List<SessionDoneMenteeResponseDTO> sessionDoneMenteeResponseDTOS = StatsMapper.convertEntityToDTOmenteeSessionDone(mentees);
        return new ResponseEntity<>(new SessionDoneMenteeResponse
                ("1",
                        "Total no of mentee  that has done session previous Week" + sessionDoneMenteeResponseDTOS.size(),
                        sessionDoneMenteeResponseDTOS), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TotalSessionResponse> getAllTheNoSessionHappened() {
        List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsBeforeCurrentTime();
        List<UUID>availabilityList1=sessionRepository.getAllAvailabilityIds();
        List<Object[]> serviceSessionCounts = sessionRepository.getSessionCountsByService(availabilityList);
//
        if (serviceSessionCounts.isEmpty()) {
            return new ResponseEntity<>
                    (new TotalSessionResponse
                            ("0",
                                    "No session has happened", null), HttpStatus.NOT_FOUND);
           }
            List<TotalSessionResponseDTO> totalSessionResponseDTOS = StatsMapper.convertdtoTodtoObject(serviceSessionCounts);
            return new ResponseEntity<>(new TotalSessionResponse
                    (
                            "1",
                            "Total no of sessions has happened " +availabilityList1.size()+
                                    "", totalSessionResponseDTOS), HttpStatus.OK);


//   }
        }
        @Override
    public ResponseEntity<TotalSessionResponse>getAllTheNoSessionHappenedPreviousDay(){
            List<UUID> availabilityList = availabilityRepository.findAvailabilityIdsForYesterday();
            List<UUID>availabilityList1=sessionRepository.getAllAvailabilityIds();
            List<Object[]> serviceSessionCounts = sessionRepository.getSessionCountsByService(availabilityList);
            if (serviceSessionCounts.isEmpty()) {
                return new ResponseEntity<>
                        (new TotalSessionResponse
                                ("0",
                                        "No session has happened", null), HttpStatus.NOT_FOUND);
            }
            List<TotalSessionResponseDTO> totalSessionResponseDTOS = StatsMapper.convertdtoTodtoObject(serviceSessionCounts);
            return new ResponseEntity<>(new TotalSessionResponse
                    (
                            "1",
                            "Total no of sessions has happened previous day " +availabilityList1.size()+
                                    "", totalSessionResponseDTOS), HttpStatus.OK);


//   }


        }
        @Override
    public ResponseEntity<TotalSessionResponse>getAllTheNoSessionHappenedPreviousWeek(){
            List<UUID> availabilityList =availabilityRepository.findAvailabilityIdsForPreviousWeek();
            List<UUID>availabilityList1=sessionRepository.getAllAvailabilityIds();
            List<Object[]> serviceSessionCounts = sessionRepository.getSessionCountsByService(availabilityList);
            if (serviceSessionCounts.isEmpty()) {
                return new ResponseEntity<>
                        (new TotalSessionResponse
                                ("0",
                                        "No session has happened", null), HttpStatus.NOT_FOUND);
            }
            List<TotalSessionResponseDTO> totalSessionResponseDTOS = StatsMapper.convertdtoTodtoObject(serviceSessionCounts);
            return new ResponseEntity<>(new TotalSessionResponse
                    (
                            "1",
                            "Total no of sessions has happened previous week " +availabilityList1.size()+
                                    "", totalSessionResponseDTOS), HttpStatus.OK);


        }


    }





















