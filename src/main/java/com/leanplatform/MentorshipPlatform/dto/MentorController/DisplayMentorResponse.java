package com.leanplatform.MentorshipPlatform.dto.MentorController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisplayMentorResponse {
    private String statusCode;
    private String responseMessage;
    private String firstName;
    private String lastName;
    private String mentorRoleApplied;
    private String currentPlaceOfEmployment;
    private List<ServicesByMentorDto> servicesByMentorsDto;
}
