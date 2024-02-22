package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatorDetailsRequestLP2 {
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
    private LandingPageRequest2 landingPageRequest2;
}
