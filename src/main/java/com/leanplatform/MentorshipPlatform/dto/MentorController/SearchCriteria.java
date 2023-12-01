package com.leanplatform.MentorshipPlatform.dto.MentorController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String companyName;
    private String mentorshipRole;
    private String serviceNeeded;
    private Integer yearsOfExperience;
}
