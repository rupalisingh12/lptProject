package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController.LandingPage2.CreateDetailsForCreatorDtoLP2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateSlotResponse{
    private String statusCode;
    private String responseMessage;
    private UpdateStatusResponseDTO updateStatusResponseDTO;
}
