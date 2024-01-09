package com.leanplatform.MentorshipPlatform.dto.AvailabilityNew;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAvailabiliityNewResponse {
    private String statusCode;
    private String responseMessage;
    private UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO;
}
