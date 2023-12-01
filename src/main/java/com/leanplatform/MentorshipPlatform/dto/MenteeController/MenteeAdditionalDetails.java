package com.leanplatform.MentorshipPlatform.dto.MenteeController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenteeAdditionalDetails {
    private UUID menteeId;
    private String educationalQualification;
    private Integer age;
    private Boolean employed;
    private String jobRole;
}
