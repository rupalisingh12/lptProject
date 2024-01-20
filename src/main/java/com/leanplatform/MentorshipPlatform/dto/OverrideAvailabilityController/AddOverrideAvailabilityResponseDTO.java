package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import com.leanplatform.MentorshipPlatform.mappers.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOverrideAvailabilityResponseDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;



}
