package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsForCreatorDtoLP2 {
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
   private LandingPage2Response landingPageRequest2;
//     private UUID userId;
//    private String landingPageId;
    private List<GetAvailabilityButtonsDto> getAvailabilityButtonsDto;
}
