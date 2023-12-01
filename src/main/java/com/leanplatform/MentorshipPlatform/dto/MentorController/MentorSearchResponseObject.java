package com.leanplatform.MentorshipPlatform.dto.MentorController;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorSearchResponseObject {
    private String statusCode;
    private String responseMessage;
    private List<MentorSearchResponseDto> mentorSearchResponseDtos;
}
