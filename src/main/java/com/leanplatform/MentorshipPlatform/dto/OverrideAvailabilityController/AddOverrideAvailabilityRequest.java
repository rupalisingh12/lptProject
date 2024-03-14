package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOverrideAvailabilityRequest {
     private LocalDateTime startTimeSlot2;
     private LocalDateTime endTimeSlot2;

}
