package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewRequest;
import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.CreateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.repositories.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;

public class AvailabilityNewMapper {
    @Autowired
    static SlotRepository slotRepository;

    public static CreateAvailabilityNewResponseDTO convertDtoToEntity(CreateAvailabilityNewRequest createAvailabilityNewRequest) {
        CreateAvailabilityNewResponseDTO createAvailabilityNewResponseDTO = new CreateAvailabilityNewResponseDTO();
        // createAvailabilityNewResponseDTO.setAvailabilityId(createAvailabilityNewRequest.getAvailabilityId());
        createAvailabilityNewResponseDTO.setScheduleId(createAvailabilityNewRequest.getScheduleId());


        //createAvailabilityNewResponseDTO.setSlotIds(createAvailabilityNewRequest.getSlotIds());
        createAvailabilityNewResponseDTO.setStartTime(createAvailabilityNewRequest.getStartTime());
        createAvailabilityNewResponseDTO.setEndTime(createAvailabilityNewRequest.getEndTime());
        createAvailabilityNewResponseDTO.setDays(createAvailabilityNewRequest.getDays());


        // createAvailabilityNewResponseDTO.setDays(availabilityNew.getDays());
        return createAvailabilityNewResponseDTO;

    }

    public static Long convertStartTimeEndTimeIntoSlotIds(LocalTime startTime, LocalTime endTime) {
        Duration duration = Duration.between(startTime, endTime);
        long minutes = duration.toMinutesPart();

        return null;
    }

    public void slotArray() {
        SlotTable[] arr = new SlotTable[48];
        LocalTime currentStartTime = LocalTime.of(0, 0, 0);
        LocalTime currentEndTime = LocalTime.of(0, 30, 0);
        for (int i = 1; i <= arr.length; i++) {
            arr[i] = new SlotTable();
            arr[i].setSlotId((long) i);
            arr[i].setStartTime(currentStartTime);
            arr[i].setEndTime(currentEndTime);
            currentStartTime.plusMinutes(30);
            currentEndTime.plusMinutes(30);
        }
    }
}






