package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface OverallStatsService {
    ResponseEntity<RegisteredMentorsResponse> getAllRegisteredMentors();
    ResponseEntity<RegisteredMentorsResponse> getRegisteredMentorsCreatedPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);
    ResponseEntity<RegisteredMentorsResponse> getRegisteredMentorsCreatedPreviousWeek(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);
    ResponseEntity<ActiveMentorsResponse>getActiveMentorsCreatedPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd );
    ResponseEntity<ActiveMentorsResponse>getAllActiveMentors();
    ResponseEntity<ActiveMentorsResponse>getAllActiveMentorsCreatedPreviousWeek(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd );
    ResponseEntity<SessionDoneMentorsResponse>getAlltheMentorsDoneSession();
    ResponseEntity<SessionDoneMentorsResponse>getAllTheMentorWhoHasDoneSessionPreviousDay(LocalDateTime yesterdayStart,LocalDateTime yesterdayEnd);
//    ResponseEntity<SessionDoneMentorsResponse>getA
    ResponseEntity<RegisteredMenteeRespone>getResgisteredMentee();
    ResponseEntity<SessionDoneMentorsResponse>getAllTheMentorwhoDidSessionPreviousWeek(LocalDateTime lastWeekStart,LocalDateTime lastWeekEnd);
//    ResponseEntity<RegisteredMenteeRespone>getAllTheMenteeWhoHasDoneSession();
    ResponseEntity<RegisteredMenteeRespone> getMenteeWhoRegisteredPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd);
    ResponseEntity<RegisteredMenteeRespone>getAllTheMenteWhoRegisteredPreviousWeek(LocalDateTime lastWeekStart,LocalDateTime lastWeekEnd);
    ResponseEntity<SessionDoneMenteeResponse>getAllthementeeWhoHasDoneSession();
   ResponseEntity<SessionDoneMenteeResponse>getMenteeWhoDoneSessionPreviousDay(LocalDateTime yesterdayStart,LocalDateTime yesterdayEnd);
   ResponseEntity<SessionDoneMenteeResponse>getMenteeWhoDoneSessionPreviousWeek(LocalDateTime lastWeekStart,LocalDateTime lastWeekEnd);
  ResponseEntity<TotalSessionResponse>getAllTheNoSessionHappened();





}
