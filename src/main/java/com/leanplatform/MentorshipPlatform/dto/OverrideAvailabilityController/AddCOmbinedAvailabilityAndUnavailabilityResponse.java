package com.leanplatform.MentorshipPlatform.dto.OverrideAvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCOmbinedAvailabilityAndUnavailabilityResponse {

    private List<AddOverrideAvailabilityResponseDTO> addOverrideAvailabilityResponseDTO;
    private List<AddOverrideUnavailabiltyResponseDTO> addOverrideUnavailabilityResponseDTO;


}
