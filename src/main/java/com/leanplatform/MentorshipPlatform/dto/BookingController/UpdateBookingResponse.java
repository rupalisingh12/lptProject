package com.leanplatform.MentorshipPlatform.dto.BookingController;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBookingResponse {
    private String statusCode;
    private String responseMessage;
    private UpdateBookingResponseDTO updateBookingResponseDTO;
}
