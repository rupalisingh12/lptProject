package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import com.leanplatform.MentorshipPlatform.dto.BookingController.CreateBookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsForCreatorResponse {
    private String statusCode;
    private String responseMessage;
    private CreateDetailsForCreatorDto createDetailsForCreatorDto;

}
