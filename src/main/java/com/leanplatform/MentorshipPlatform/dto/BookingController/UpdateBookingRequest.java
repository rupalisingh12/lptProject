package com.leanplatform.MentorshipPlatform.dto.BookingController;

import com.leanplatform.MentorshipPlatform.enums.BookingEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateBookingRequest {
    private String status;
}

