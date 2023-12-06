package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponse;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.RegisteredMenteeRespone;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.RegisteredMentorsResponse;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.SessionDoneMentorsResponse;
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




}
