package com.leanplatform.MentorshipPlatform.dto.MentorController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MentorSearchResponseDto {
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String resume;
    private String email;
    private String linkedIn_link;
    private int yearsOfExperience;
    private String lastPlaceOfEmployment;
    private String currentPlaceOfEmployment;
    private String priorToLastPlaceOfEmployment;
    private String prioritySkill;
}
