package com.leanplatform.MentorshipPlatform.dto.BookingController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetBookingResponse {
    private String statusCode;
    private String responseMessage;
    private List<CreateBookingDTO> bookings;
}
