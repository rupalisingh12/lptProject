package com.leanplatform.MentorshipPlatform.dto.SessionController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SessionSummaryResponse {
    private String statusCode;
    private String responseMessage;
    private String sessionSummary;
}
