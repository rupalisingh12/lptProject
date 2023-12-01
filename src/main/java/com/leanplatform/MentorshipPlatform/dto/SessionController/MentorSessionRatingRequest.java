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
public class MentorSessionRatingRequest {
    private UUID sessionId;
    private UUID mentorId;
    private UUID menteeId;
    private UUID serviceId;
    private String feedback;
    private Double sessionRating;
}
