package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOverrideAvailabilityRespone {
    private String statusCode;
    private String responseMessage;
    private AddCOmbinedAvailabilityAndUnavailabilityResponse addCOmbinedAvailabilityAndUnavailabilityResponse;

}
