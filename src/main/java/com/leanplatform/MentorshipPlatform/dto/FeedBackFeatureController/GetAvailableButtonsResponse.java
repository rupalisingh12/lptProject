package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAvailableButtonsResponse {
    private String statusCode;
    private String responseMessage;
    private List<GetAvailabilityButtonsDto> getAvailabilityButtonsDto;



}
