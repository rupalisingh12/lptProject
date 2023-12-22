package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.ScheduleController.AvailabilityNewDTO;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleResponse;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleResponseDTO;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleMapper {
    public static CreateScheduleResponseDTO convertEntityToDTO(Schedule schedule, List<AvailabilityNew>availabilityNewList) {
        CreateScheduleResponseDTO createScheduleResponsedto = new CreateScheduleResponseDTO();
        createScheduleResponsedto.setScheduleId(schedule.getScheduleId());
        createScheduleResponsedto.setName(schedule.getName());
        List<AvailabilityNew> availabilityNewList1 = new ArrayList<>();
        AvailabilityNewDTO availabilityNewDTO = null;
        for (int i = 0; i < availabilityNewList.size(); i++) {
            AvailabilityNew availabilityNewobj = availabilityNewList.get(i);
            availabilityNewDTO = convertToDTO(availabilityNewobj);
            availabilityNewList1.add(availabilityNewobj);
        }

        createScheduleResponsedto.setAvailabiltyNewDTO(availabilityNewDTO);

        return createScheduleResponsedto;
    }

    private static AvailabilityNewDTO convertToDTO(AvailabilityNew availabilityNew) {
        AvailabilityNewDTO availabilityNewDTO = new AvailabilityNewDTO();
        availabilityNewDTO.setAvailabilityId(availabilityNew.getAvailabilityId());
        availabilityNewDTO.setScheduleId(availabilityNew.getScheduleId());
        //availabilityNewDTO.setDays(availabilityNew.getDays());
        return availabilityNewDTO;







    }
}
