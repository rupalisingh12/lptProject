package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAccount;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeAdditionalDetails;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeModified;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MenteeService {
    ResponseEntity<MenteeModified> createMenteeAccount(MenteeAccount menteeAccount);

    ResponseEntity<MenteeModified> updateMenteeAccount(MenteeAdditionalDetails menteeAdditionalDetails);

    boolean isEmailAlreadyExists(String email);

    boolean isPhoneNumberAlreadyExists(String phoneNo);
}
