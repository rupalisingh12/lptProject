package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRequest;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityRespone;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.AddOverrideAvailabilityResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.DeleteOverrideRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface OverrideAvailabilityService {
//    public ResponseEntity<AddOverrideAvailabilityRespone>addOverrideAvailability(UUID scheduleId, List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequestO);
     ResponseEntity<AddOverrideAvailabilityRespone>UpdateOverrideAvailability(UUID scheduleId,List<AddOverrideAvailabilityRequest>addOverrideAvailabilityRequest);
     ResponseEntity<AddOverrideAvailabilityRespone> GetOverrideAvailability(UUID scheduleId);
     ResponseEntity<AddOverrideAvailabilityRespone>DeleteOverrideAvailability(UUID scheduleId, DeleteOverrideRequest deleteOverrideRequest);
}
