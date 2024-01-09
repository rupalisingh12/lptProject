package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.*;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestObject;
import com.leanplatform.MentorshipPlatform.dto.MentorAccountController.MentorRequestResponse;
import com.leanplatform.MentorshipPlatform.services.implementation.AvailabilityNewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
@Service
public interface AvailabilityNewService {



     ResponseEntity<UpdateAvailabiliityNewResponse> addAnAvailability(UUID scheduleId, CreateAvailabilityNewRequest createAvailabilityNewRequest) ;
     ResponseEntity<GetAllAvailabilitiesResponse>getAllAvailability(String userName, UUID userId, UUID eventTypeId, LocalDate dateTo, LocalDate dateFrom);
     ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailabilitys(UUID scheduleId, UpdateAvailabilityNewRequest updateAvailabilityNewRequest);
     ResponseEntity<DeleteAvailabilityResponse> deleteAvailability(UUID scheduleId,Long day);

}
