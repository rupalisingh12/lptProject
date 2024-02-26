package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.CreateDetailsForCreatorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsResponseForCreatorLP2 {
    private String statusCode;
    private String responseMessage;
    private CreateDetailsForCreatorDtoLP2 createDetailsForCreatorDtoLP2;
}
