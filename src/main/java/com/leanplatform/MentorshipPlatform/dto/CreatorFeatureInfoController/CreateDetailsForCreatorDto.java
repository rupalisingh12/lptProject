package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.FeedBackFeatureController.GetAvailabilityButtonsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDetailsForCreatorDto {
    private String leadGenForm;
    private String masterClass;
    private Boolean slot;
    private  List<AddCoursesResponseDTO> addCoursesResponseDTO;
//   private UUID userId;
//   private String landingPageId; //the id which is creattor ging to choose out of the two landingPage
    private LandingPageResponse landingPageResponse;
   private List<GetAvailabilityButtonsDto> feedBackEnabled;

}
