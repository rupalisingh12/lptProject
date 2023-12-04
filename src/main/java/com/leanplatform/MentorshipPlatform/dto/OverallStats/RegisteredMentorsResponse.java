package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import com.leanplatform.MentorshipPlatform.entities.MentorRequest;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class RegisteredMentorsResponse {
    private String statusCode;
    private String responseMessage;
    private List<RegisteredMentorsResponseDTO> listOfRegisteredMentors;
}
