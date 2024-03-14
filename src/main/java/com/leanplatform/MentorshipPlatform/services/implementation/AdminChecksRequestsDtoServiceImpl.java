package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityController.AdminChecksRequestsDto;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.MentorRequest;
import com.leanplatform.MentorshipPlatform.services.AdminChecksRequestsDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminChecksRequestsDtoServiceImpl implements AdminChecksRequestsDtoService {

    @Override
    public List<AdminChecksRequestsDto> convertToDTOList(List<MentorRequest> mentorRequests) {
        List<AdminChecksRequestsDto> adminChecksRequestsDtos = new ArrayList<>();
        for (MentorRequest mentorRequest : mentorRequests) {
                AdminChecksRequestsDto adminChecksRequestsDto = new AdminChecksRequestsDto();
                adminChecksRequestsDto.setId(mentorRequest.getId());
                adminChecksRequestsDto.setResume(mentorRequest.getResume());
                adminChecksRequestsDto.setStatus(String.valueOf(mentorRequest.getStatus()));
                adminChecksRequestsDto.setMentorRoleApplied(mentorRequest.getMentorRoleApplied());
                adminChecksRequestsDtos.add(adminChecksRequestsDto);
        }
        return adminChecksRequestsDtos;
    }
}
