package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.*;
import com.leanplatform.MentorshipPlatform.services.OverallStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/stats")
public class OverallStatsController {

    @Autowired
    private OverallStatsService overallStatsService;

    //to get overall registered mentors
    @GetMapping("/registeredMentors")
    public ResponseEntity<RegisteredMentorsResponse> getAllTheRegisteredMentors() {
        // return  mentorAccountService.getAllRegisteredMentors();
        try {
            return overallStatsService.getAllRegisteredMentors();
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMentorsResponse
                    (
                            "0",
                            "Error: " + e.getLocalizedMessage(),
                            null
                    ), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    // to get registered mentors in previous day
    @GetMapping("/registeredMentorsPreviousDay")
    public ResponseEntity<RegisteredMentorsResponse> getMentorsCreatedPreviousDay() {
        try {
            // Calculate timestamps for the previous day
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

            // Call the service method to get mentors created on the previous day
            return overallStatsService.getRegisteredMentorsCreatedPreviousDay(yesterdayStart, yesterdayEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "Error: " + e.getLocalizedMessage(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // to get registred mentors in previous week
    @GetMapping("/registeredMentorsLastWeek")
    public ResponseEntity<RegisteredMentorsResponse> getMentorsCreatedLastWeek() {
        try {
            // Calculate timestamps for the last week
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

            // Call the service method to get mentors created in the last week
            return overallStatsService.getRegisteredMentorsCreatedPreviousWeek(lastWeekStart, lastWeekEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "Error: " + e.getLocalizedMessage(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // to get overall active mentors
    @GetMapping("/activeMentors")
    public ResponseEntity<ActiveMentorsResponse> getAllActiveMentor() {
        try {
            return overallStatsService.getAllActiveMentors();


        } catch (Exception e) {
            return new ResponseEntity<>
                    (new ActiveMentorsResponse
                            ("0",
                                    "Error:" + e.getLocalizedMessage(),
                                    null), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/activePreviousDay")
    public ResponseEntity<ActiveMentorsResponse> getAllActiveMentorsPreviousDay() {
        try {
            // Calculate timestamps for the previous day
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

            // Call the service method to get mentors created on the previous day
            return overallStatsService.getActiveMentorsCreatedPreviousDay(yesterdayStart, yesterdayEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new ActiveMentorsResponse(
                    "0",
                    "Error: " + e.getLocalizedMessage(),
                    null
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activeMentorsPreviousWeek")
    public ResponseEntity<ActiveMentorsResponse> getAllActiveMentorsPreviousWeek() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getAllActiveMentorsCreatedPreviousWeek(lastWeekStart, lastWeekEnd);

        } catch (Exception e) {
            return new ResponseEntity<>(new ActiveMentorsResponse
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/sessionMentors")
    public ResponseEntity<SessionDoneMentorsResponse> getALlTheMentorWhoHasDoneSession() {
        try {
            return overallStatsService.getAlltheMentorsDoneSession();

        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    @GetMapping("/sessionMentorsPreviousDay")
    public ResponseEntity<SessionDoneMentorsResponse> getAllTheMentorsWhoHasDoneSessionPreviousDay() {
        try {

            // Calculate timestamps for the previous day
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getAllTheMentorWhoHasDoneSessionPreviousDay(yesterdayStart, yesterdayEnd);
        } catch (Exception e) {

            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/sessionMentorsPreviousWeek")
    public ResponseEntity<SessionDoneMentorsResponse> getAllTheMentorsWhoHasDoneSessionPreviousWeek() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getAllTheMentorwhoDidSessionPreviousWeek(lastWeekStart, lastWeekEnd);

        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDoneMentorsResponse
                    ("0",
                            "Error" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/registeredMentee")
    public ResponseEntity<RegisteredMenteeRespone> getALlThementee() {
        try {
            return overallStatsService.getResgisteredMentee();
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMenteeRespone
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/registeredMenteePreviousDay")
    public ResponseEntity<RegisteredMenteeRespone> getMenteeWhoHasDoneSessions() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getMenteeWhoRegisteredPreviousDay(yesterdayStart, yesterdayEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMenteeRespone
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/registeredMenteePreviousWeek")
    public ResponseEntity<RegisteredMenteeRespone> getMenteeWHoRegisteredPreviousWeek() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getAllTheMenteWhoRegisteredPreviousWeek(lastWeekStart, lastWeekEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMenteeRespone
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);


        }


    }

    @GetMapping("/menteeWhoHasDoneSession")
    public ResponseEntity<SessionDoneMenteeResponse> getMenteeWhoHasDoneSession() {
        try {
            return overallStatsService.getAllthementeeWhoHasDoneSession();
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDoneMenteeResponse
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }

    @GetMapping("/menteeWhoHasDoneSessionPreviousDay")
    public ResponseEntity<SessionDoneMenteeResponse> getMenteeDoneSessionPreviousDay() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getMenteeWhoDoneSessionPreviousDay(yesterdayStart,yesterdayEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDoneMenteeResponse
                    ("0", "Error:" + e.getLocalizedMessage(),
                            null), HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
    @GetMapping("/menteeWhoHasDoneSessionPreviousWeek")
    public ResponseEntity<SessionDoneMenteeResponse>getMenteeDoneSessionPreviousWeek() {
        try {
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
            return overallStatsService.getMenteeWhoDoneSessionPreviousWeek(lastWeekStart,lastWeekEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new SessionDoneMenteeResponse
                    ("0", "Error" + e.getLocalizedMessage()
                            , null), HttpStatus.INTERNAL_SERVER_ERROR);

       }
    }
    @GetMapping("NoOfSession")
    public ResponseEntity<TotalSessionResponse>getNoOfSessionHappened(){
        try{
            return overallStatsService.getAllTheNoSessionHappened();

        }catch(Exception e){
            return new ResponseEntity<>
                    (new TotalSessionResponse
                            ("0","Error"+e.getLocalizedMessage(),
                                    null),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//
////


}


















