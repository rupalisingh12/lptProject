package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddSuggestionResponse {
    private String statusCode;
    private String responseMessage;
    private AddSuggestionResponseDTO addSuggestionResponseDTO;
}
