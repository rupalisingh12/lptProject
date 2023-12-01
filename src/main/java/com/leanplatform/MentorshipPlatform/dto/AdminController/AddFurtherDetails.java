package com.leanplatform.MentorshipPlatform.dto.AdminController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddFurtherDetails {
    private UUID mentor_id;
    private String lastPlaceOfEmployment;
    private String currentPlaceOfEmployment;
    private String priorToLastPlaceOfEmployment;
    private String prioritySkill;
    private String educationQualification;
}
