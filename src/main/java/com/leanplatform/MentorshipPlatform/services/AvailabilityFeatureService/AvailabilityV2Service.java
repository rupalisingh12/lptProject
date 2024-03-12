package com.leanplatform.MentorshipPlatform.services.AvailabilityFeatureService;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
@Service
public interface AvailabilityV2Service {



     ResponseEntity<UpdateAvailabiliityNewResponse> addAnAvailability(UUID userId, CreateAvailabilityNewRequest createAvailabilityNewRequest) ;
     ResponseEntity<GetAllAvailabilitiesResponse>getAllAvailability(String userName, UUID userId, UUID eventTypeId,  LocalDate dateFrom,LocalDate dateTo);
     ResponseEntity<UpdateAvailabiliityNewResponse>updateAvailabilitys(UUID scheduleId,UUID userId, UpdateAvailabilityNewRequest updateAvailabilityNewRequest);
     ResponseEntity<DeleteAvailabilityResponse> deleteAvailability(UUID scheduleId,Long day);

}
