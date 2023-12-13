package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SessionDoneMenteeResponseDTO {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String educationalQualification;
    private Integer age;


}
