package com.leanplatform.MentorshipPlatform.dto.SessionController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSessionDetails {
    private UUID sessionId;
    private String reasonForSession;
    private String sessionExpectations;
    private String sessionSummary;
}
