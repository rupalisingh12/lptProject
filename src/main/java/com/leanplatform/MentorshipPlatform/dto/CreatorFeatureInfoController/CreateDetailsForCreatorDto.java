package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsForCreatorDto {
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
    private LandingPageResponse landingPageResponse;
}
