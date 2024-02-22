package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplySectionDto {
    private String applyHeading;
    private String applySubHeading;
    private  String applyImg;
    private BulletPointsOfBody applyBody;
    private String ctaSection;
    private String ctaNavigation;


}
