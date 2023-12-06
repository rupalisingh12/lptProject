package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SessionDoneMentorsResponse {
    private String statusCode;
    private String responseMessage;
    private List<RegisteredDoneMentorsResponseDTO> listOfRegisteredMentors;
}
