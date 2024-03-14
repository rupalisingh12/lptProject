package com.leanplatform.MentorshipPlatform.dto.CoursesController;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class DoMeetingResponse {
    private String statusCode;
    private String responseMessage;
    private DoMeetingResponseDTO doMeetingResponseDTO;
}
