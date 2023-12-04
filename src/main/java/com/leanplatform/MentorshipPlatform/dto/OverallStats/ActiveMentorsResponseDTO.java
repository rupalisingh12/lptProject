package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ActiveMentorsResponseDTO {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String resume;
    private String email;
    private String linkedIn_link;
    private Integer yearsOfExperience;

    private String educationQualification;
    private Double overAllRating;

}
