package com.leanplatform.MentorshipPlatform.controllers;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponse;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.RegisteredMentorsResponse;
import com.leanplatform.MentorshipPlatform.services.MentorAccountService;
import com.leanplatform.MentorshipPlatform.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/stats")
public class OverallStats {
    @Autowired
    private MentorAccountService mentorAccountService;
    @Autowired
    private MentorService mentorService;
   //to get overall registered mentors
    @GetMapping("/registeredMentors")
    public ResponseEntity<RegisteredMentorsResponse>getAllTheRegisteredMentors() {
        // return  mentorAccountService.getAllRegisteredMentors();
        try {
            return mentorAccountService.getAllRegisteredMentors();
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMentorsResponse
                    (
                            "0",
                            "Error: " + e.getLocalizedMessage(),
                            null
                    ), HttpStatus.BAD_REQUEST);

        }
    }
    // to get registered mentors in previous day
        @GetMapping("/registeredMentorsPreviousDay")
        public ResponseEntity<RegisteredMentorsResponse> getMentorsCreatedPreviousDay(){
            try {
                // Calculate timestamps for the previous day
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

                // Call the service method to get mentors created on the previous day
                return mentorAccountService.getRegisteredMentorsCreatedPreviousDay(yesterdayStart,yesterdayEnd );
            } catch (Exception e) {
                return new ResponseEntity<>(new RegisteredMentorsResponse(
                        "0",
                        "Error: " + e.getLocalizedMessage(),
                        null
                ), HttpStatus.BAD_REQUEST);
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
            return mentorAccountService.getRegisteredMentorsCreatedPreviousWeek(lastWeekStart, lastWeekEnd);
        } catch (Exception e) {
            return new ResponseEntity<>(new RegisteredMentorsResponse(
                    "0",
                    "Error: " + e.getLocalizedMessage(),
                    null
            ), HttpStatus.BAD_REQUEST);
        }
    }

        // to get overall active mentors
        @GetMapping("/activeMentors")
        public ResponseEntity<ActiveMentorsResponse>getAllActiveMentor(){
            try{
                return mentorService.getAllActiveMentors();


            }
            catch (Exception e){
                return new ResponseEntity<>
                        (new ActiveMentorsResponse
                                      ("0",
                                        "Error:"+e.getLocalizedMessage(),
                                        null),HttpStatus.BAD_REQUEST);

            }

        }
        @GetMapping("/activePreviousDay")
    public ResponseEntity<ActiveMentorsResponse>getAllActiveMentorsPreviousDay(){
            try {
                // Calculate timestamps for the previous day
                LocalDateTime today = LocalDateTime.now();
                LocalDateTime yesterdayStart = today.minusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
                LocalDateTime yesterdayEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

                // Call the service method to get mentors created on the previous day
                return mentorService.getActiveMentorsCreatedPreviousDay(yesterdayStart,yesterdayEnd );
            } catch (Exception e) {
                return new ResponseEntity<>(new ActiveMentorsResponse(
                        "0",
                        "Error: " + e.getLocalizedMessage(),
                        null
                ), HttpStatus.BAD_REQUEST);
            }
        }
        @GetMapping("/activeMentorsPreviousWeek")
        public ResponseEntity<ActiveMentorsResponse>getAllActiveMentorsPreviousWeek(){
        try{
            LocalDateTime today = LocalDateTime.now();
            LocalDateTime lastWeekStart = today.minusDays(7).withHour(0).withMinute(0).withSecond(0).withNano(0);
            LocalDateTime lastWeekEnd = today.withHour(0).withMinute(0).withSecond(0).withNano(0);
             return mentorService.getAllActiveMentorsCreatedPreviousWeek(lastWeekStart,lastWeekEnd);

        }
        catch(Exception e){
            return new ResponseEntity<>(new ActiveMentorsResponse
                    ("0","Error:"+e.getLocalizedMessage(),
                            null),HttpStatus.BAD_REQUEST);

            }
        }



    }













