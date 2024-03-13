package com.leanplatform.MentorshipPlatform.dto.CreatorFeatureInfoController;

import com.leanplatform.MentorshipPlatform.dto.CoursesController.AddCoursesResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCourseResponse2 {
    private String title;
    private String heading;
    private String subHeading;
    private List<AddCoursesResponseDTO> addCoursesResponseDTOS;
}
