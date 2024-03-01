package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRatingRequest {
    private String numberOfStars;
    private String emailId;
}
