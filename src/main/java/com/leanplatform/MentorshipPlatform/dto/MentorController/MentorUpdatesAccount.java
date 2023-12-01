package com.leanplatform.MentorshipPlatform.dto.MentorController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorUpdatesAccount {
    private UUID mentor_id;
    private String resume;
    private String linkedIn_link;
    private Integer yearsOfExperience;
    private String mentorRoleApplied;
    private String lastPlaceOfEmployment;
    private String currentPlaceOfEmployment;
    private String priorToLastPlaceOfEmployment;
    private String prioritySkill;
}
