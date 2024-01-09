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
public class DeleteSchedule {
    private String statusCode;
    private String responseMessage;
    private  DeleteScheduleDTO deleteScheduleDTO;
}
