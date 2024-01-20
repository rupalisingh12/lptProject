package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import com.leanplatform.MentorshipPlatform.mappers.Slot;
import com.leanplatform.MentorshipPlatform.mappers.Slot2;
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
