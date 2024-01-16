package com.leanplatform.MentorshipPlatform.dto.BookingController;

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
