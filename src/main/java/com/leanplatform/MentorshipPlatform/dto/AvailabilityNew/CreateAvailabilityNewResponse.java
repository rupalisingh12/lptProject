package com.leanplatform.MentorshipPlatform.dto.AvailabilityNew;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.AvailabilityByMentor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAvailabilityNewResponse {
    private String statusCode;
    private String responseMessage;
    private CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO;
}
