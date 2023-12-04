package com.leanplatform.MentorshipPlatform.dto.OverallStats;

import com.leanplatform.MentorshipPlatform.enums.MentorEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisteredMentorsResponseDTO {

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String resume;

    private String linkedIn_link;
    private int yearsOfExperience;
   ;

}
