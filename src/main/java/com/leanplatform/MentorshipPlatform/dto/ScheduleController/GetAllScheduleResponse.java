package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllScheduleResponse {
    private String statusCode;
    private String responseMessage;
    private List<CreateScheduleResponseDTO> schedules;
}
