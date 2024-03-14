package com.leanplatform.MentorshipPlatform.services.OverrideAvailabilityFeatureService;

import com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController.*;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OverrideAvailabilityService {
//    public ResponseEntity<AddOverrideAvailabilityRespone>addOverrideAvailability(UUID scheduleId, List<AddOverrideAvailabilityRequest> addOverrideAvailabilityRequestO);
     ResponseEntity<AddOverrideAvailabilityRespone>UpdateOverrideAvailability(UUID scheduleId,AddavailabilityOverrideCombinedRequest addavailabilityOverrideCombinedRequest);
     ResponseEntity<AddOverrideAvailabilityRespone> GetOverrideAvailability(UUID scheduleId);
     ResponseEntity<AddOverrideAvailabilityRespone>DeleteOverrideAvailability(UUID scheduleId, DeleteOverrideRequest deleteOverrideRequest);
     ResponseEntity<AddOverrideAvailabilityRespone>DeleteOverrideUnAvailabilitys(UUID scheduleId,DeleteOverrideUnavailabilityRequest deleteOverrideUnavailability);
}
