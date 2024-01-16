package com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllAvailabilitiesResponseDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
