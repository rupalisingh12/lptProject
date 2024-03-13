package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExtraDeatilsRequest {
    private String overview;
    private List<String> whoThisCourseIsFor;
    private List<String>thisCourseIncludes;


}
