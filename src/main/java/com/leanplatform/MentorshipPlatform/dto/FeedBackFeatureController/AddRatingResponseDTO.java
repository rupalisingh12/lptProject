package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddRatingResponseDTO {

    private String numberOfStarts;
    private  String emailId;
    private LocalDateTime createdAt;

}
