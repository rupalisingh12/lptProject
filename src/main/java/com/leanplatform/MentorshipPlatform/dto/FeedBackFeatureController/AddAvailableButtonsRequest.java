package com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddAvailableButtonsRequest {
   private Boolean contact;
   private Boolean suggestion;
   private Boolean feedBack;
   private Boolean issue;
   private Boolean rating;
}
