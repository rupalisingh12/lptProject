package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;

import java.util.List;

public class AvailabilityNewMapper {
    public static CreateAvailabilityNewResponseDTO convertDtoToEntity(AvailabilityNew availabilityNew, List<Long> ans){
         CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO=new CreateAvailabilityNewResponseDTO();
         createAvailabilityNewResponseDTO.setAvailabilityId(availabilityNew.getAvailabilityId());
         createAvailabilityNewResponseDTO.setScheduleId(availabilityNew.getScheduleId());
         createAvailabilityNewResponseDTO.setStartTime(availabilityNew.getStartTime());
         createAvailabilityNewResponseDTO.setDays(ans);
         createAvailabilityNewResponseDTO.setEndTime(availabilityNew.getEndTime());

        // createAvailabilityNewResponseDTO.setDays(availabilityNew.getDays());
         return createAvailabilityNewResponseDTO;

    }


}
