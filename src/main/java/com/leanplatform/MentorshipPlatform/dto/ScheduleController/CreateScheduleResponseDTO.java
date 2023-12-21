package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleResponseDTO {
    private UUID scheduleId;
    private UUID userId;
    private String name;
}
