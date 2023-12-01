package com.leanplatform.MentorshipPlatform.dto.SessionController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SessionDetailsResponse {
    private String statusCode;
    private String responseMessage;
    private SessionDetailsDto SessionDetailsDto;
}
