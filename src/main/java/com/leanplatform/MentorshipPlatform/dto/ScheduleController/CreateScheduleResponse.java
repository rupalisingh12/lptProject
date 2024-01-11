package com.leanplatform.MentorshipPlatform.dto.ScheduleController;

import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleResponse {
    private String statusCode;
    private String responseMessage;
    private CreateScheduleResponseDTO Schedule;

}
