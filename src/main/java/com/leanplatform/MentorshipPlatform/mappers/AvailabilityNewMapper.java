package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Slot;
import com.leanplatform.MentorshipPlatform.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityNewMapper {
    @Autowired
    static SlotRepository slotRepository;

    public static CreateAvailabilityNewResponseDTO convertDtoToEntity(AvailabilityNew availabilityNew){
         CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO=new CreateAvailabilityNewResponseDTO();
         createAvailabilityNewResponseDTO.setAvailabilityId(availabilityNew.getAvailabilityId());
         createAvailabilityNewResponseDTO.setScheduleId(availabilityNew.getScheduleId());


         createAvailabilityNewResponseDTO.setSlotIds(availabilityNew.getSlotIds());

         createAvailabilityNewResponseDTO.setDays(availabilityNew.getDay());


        // createAvailabilityNewResponseDTO.setDays(availabilityNew.getDays());
         return createAvailabilityNewResponseDTO;

    }





}
