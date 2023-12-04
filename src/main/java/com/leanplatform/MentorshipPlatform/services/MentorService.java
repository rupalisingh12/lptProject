package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AdminController.AddFurtherDetails;
import com.leanplatform.MentorshipPlatform.dto.AdminController.AdminAddsDetailsResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.DeleteMentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestDeletedResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorController.*;
import com.leanplatform.MentorshipPlatform.dto.OverallStats.ActiveMentorsResponse;
import com.leanplatform.MentorshipPlatform.entities.Mentor;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

import java.util.List;

public interface MentorService {

    ResponseEntity<AdminAddsDetailsResponse> addAdditional(AddFurtherDetails furtherDetails);

    ResponseEntity<List<MentorSearchResponseDto>> sortMentorsByYOE();

    ResponseEntity<List<MentorSearchResponseDto>> getAllMentors();

    ResponseEntity<MentorSearchResponseObject> searchMentors(SearchCriteria criteria);

    ResponseEntity<MentorUpdatesAccountResponse> updateMentorObject(MentorUpdatesAccount mentorUpdatesAccount);

    ResponseEntity<DisplayMentorResponse> displayMentor(DisplayMentorObject displayMentorObject);

    ResponseEntity<MentorRequestDeletedResponse> deleteMentor(DeleteMentorRequestObject deleteMentorRequestObject);
    ResponseEntity<ActiveMentorsResponse>getActiveMentorsCreatedPreviousDay(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd );
    ResponseEntity<ActiveMentorsResponse>getAllActiveMentors();
    ResponseEntity<ActiveMentorsResponse>getAllActiveMentorsCreatedPreviousWeek(LocalDateTime yesterdayStart, LocalDateTime yesterdayEnd );



}
