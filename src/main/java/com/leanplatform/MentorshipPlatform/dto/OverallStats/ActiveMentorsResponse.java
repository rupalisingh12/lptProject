package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class ActiveMentorsResponse {
    private String statusCode;
    private String responseMessage;
    private List<ActiveMentorsResponseDTO> listOfRegisteredMentors;
}
