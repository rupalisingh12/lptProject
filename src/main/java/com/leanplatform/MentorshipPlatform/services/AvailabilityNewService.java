package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponse;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestResponse;
import com.leanplatform.MentorshipPlatform.services.implementation.AvailabilityNewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface AvailabilityNewService {



     ResponseEntity<CreateAvailabilityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) ;


}
