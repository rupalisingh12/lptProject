package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddHeadingRequest {
    private String title;
    private String heading;
    private String subheading;
}
