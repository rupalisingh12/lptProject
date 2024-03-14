package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.AdminChecksRequestsDto;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;

import java.util.List;

public interface AdminChecksRequestsDtoService {
    List<AdminChecksRequestsDto> convertToDTOList(List<MentorRequest> mentorRequests);
}
