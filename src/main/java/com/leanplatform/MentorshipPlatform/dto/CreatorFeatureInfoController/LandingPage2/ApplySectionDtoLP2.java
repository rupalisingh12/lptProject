package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplySectionDtoLP2 {
    private String heading;
    private String subHeading;
    private String img;
    private String cta;
    private String ctaNavigation;
    private BodyOfApplySectionDto bodyOfApplySectionDto;
}
