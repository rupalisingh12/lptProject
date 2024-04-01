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
public class GetMenteeWhoBookedSameSlotResponse {
    private String responseCode;
    private String responseMessage;
    private List<AttendeeDetailsDTO> attendeeDetailsDto;
}
