package com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAvailabilityNewRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID scheduleId;
    private List<Long> days;


}
