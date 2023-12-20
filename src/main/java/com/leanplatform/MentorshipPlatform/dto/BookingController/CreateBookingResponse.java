package com.leanplatform.MentorshipPlatform.dto.BookingController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateBookingResponse {
    private String statusCode;
    private String responseMessage;
    private CreateBookingDTO createBookingDTO;



}
