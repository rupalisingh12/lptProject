package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.CreateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.mappers.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddOverrideAvailabilityRespone {
    private String statusCode;
    private String responseMessage;
    private AddCOmbinedAvailabilityAndUnavailabilityResponse addCOmbinedAvailabilityAndUnavailabilityResponse;

}
