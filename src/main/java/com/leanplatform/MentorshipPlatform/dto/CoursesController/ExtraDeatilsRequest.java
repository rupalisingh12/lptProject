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
    private String about;
    private String field1;
    private String field2;
    private String field3;
    private List<String> field4;
    private String field5;
    private List<String> field6;

}
