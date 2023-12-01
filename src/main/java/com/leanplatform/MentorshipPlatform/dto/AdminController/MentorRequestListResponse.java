package com.leanplatform.MentorshipPlatform.dto.AdminController;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.AdminChecksRequestsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MentorRequestListResponse {
    private String statusCode;
    private String responseMessage ;
    private List<AdminChecksRequestsDto> adminChecksRequestsDtos;
}
