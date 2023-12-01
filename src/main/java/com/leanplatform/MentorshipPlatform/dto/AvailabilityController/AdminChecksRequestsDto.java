package com.leanplatform.MentorshipPlatform.dto.AvailabilityController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminChecksRequestsDto {

    private UUID id;
    private String resume;
    private String status;
    private String mentorRoleApplied;
}
