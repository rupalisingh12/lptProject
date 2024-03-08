package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

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
public class CreateDetailsForCreatorDto {
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
//   private UUID userId;
//   private String landingPageId; //the id which is creattor ging to choose out of the two landingPage
    private LandingPageResponse landingPageResponse;
   private List<GetAvailabilityButtonsDto> getAvailabilityButtonsDto;

}
