package com.leanplatform.MentorshipPlatform.dto.AvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityByMentorResponse {
    private String statusCode;
    private String responseMessage;
    private AvailabilityByMentor availabilityByMentor;
}
