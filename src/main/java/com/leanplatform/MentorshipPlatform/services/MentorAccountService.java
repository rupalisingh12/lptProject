package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AdminController.AdminDecidesRequestResponse;
import com.leanplatform.MentorshipPlatform.dto.AdminController.FetchDesiredRequestDto;
import com.leanplatform.MentorshipPlatform.dto.AdminController.MentorRequestListResponse;
import com.leanplatform.MentorshipPlatform.dto.AdminController.RequestToBeUpdated;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface MentorAccountService {
    ResponseEntity<MentorRequestResponse> creatingMentorAccount(MentorRequestObject mentorRequestObject);

    ResponseEntity<MentorRequestResponse> partialUpdate(MentorUpdateRequestObject updatedMentorRequestObject) throws IOException;


//    List<MentorRequestObject> getAllMentorRequests();


    ResponseEntity<MentorRequestListResponse> getDesiredMentorRequests(FetchDesiredRequestDto desiredRequestDto);

    ResponseEntity<AdminDecidesRequestResponse> processMentorRequest(RequestToBeUpdated adminRequest);

    ResponseEntity<GetMentorRequestResponseObject> getMentorRequests(GetMentorRequestObject getMentorRequestObject);

    boolean isEmailAlreadyExists(String email);

    ResponseEntity<MentorRequestDeletedResponse> deleteMentorRequest(DeleteMentorRequestObject deleteMentorRequestObject);

    boolean isPhoneNumberAlreadyExists(String phoneNo);
}
