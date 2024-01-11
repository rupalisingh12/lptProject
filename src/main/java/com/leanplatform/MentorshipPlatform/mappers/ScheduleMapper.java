package com.leanplatform.MentorshipPlatform.mappers;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityNew.UpdateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.leanplatform.MentorshipPlatform.mappers.AvailabilityNewMapper.catchSlotIdsListAndConvertIntoStartTimeEndTime;

public class ScheduleMapper {
    public static CreateScheduleResponseDTO convertEntityToDTO(Schedule schedule, List<AvailabilityNew>availabilityNewList) {
        CreateScheduleResponseDTO createScheduleResponsedto = new CreateScheduleResponseDTO();
        createScheduleResponsedto.setScheduleId(schedule.getScheduleId());
        createScheduleResponsedto.setUserId(schedule.getUserId());
        createScheduleResponsedto.setName(schedule.getName());
        UpdateAvailabilityNewResponseDTO  updateAvailabilityNewResponseDTO=new UpdateAvailabilityNewResponseDTO();
//        List<AvailabilityNew> availabilityNewList1 = new ArrayList<>();
//        AvailabilityNewDTO availabilityNewDTO = null;
        for (int i = 0; i < availabilityNewList.size(); i++) {

                AvailabilityNew availabilityNew = availabilityNewList.get(i);
                //  availabilityNew.getSlotIds();
                Long day = availabilityNewList.get(i).getDay();
                List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(availabilityNew.getSlotIds());
                if (day == 1) {
                    List<Slot> mon = ans;
                    updateAvailabilityNewResponseDTO.setMon(mon);

                }
                if (day == 2) {
                    List<Slot> tue = ans;
                    updateAvailabilityNewResponseDTO.setTue(tue);
                }
                if (day == 3) {
                    List<Slot> wed = ans;
                    updateAvailabilityNewResponseDTO.setWed(wed);

                }
                if (day == 4) {
                    List<Slot> thur = ans;
                    updateAvailabilityNewResponseDTO.setThur(thur);
                }
                if (day == 5) {
                    List<Slot> fri = ans;
                    updateAvailabilityNewResponseDTO.setFri(fri);
                }
                if (day == 6) {
                    List<Slot> sat = ans;
                    updateAvailabilityNewResponseDTO.setSat(sat);
                }
                if (day == 0) {
                    List<Slot> sun = ans;
                    updateAvailabilityNewResponseDTO.setSun(sun);
                }

//            AvailabilityNew availabilityNewobj = availabilityNewList.get(i);
//            availabilityNewDTO = convertToDTO(availabilityNewobj);
//            availabilityNewList1.add(availabilityNewobj);
        }
        createScheduleResponsedto.setAvailability(updateAvailabilityNewResponseDTO);

//        createScheduleResponsedto.setAvailabiltyNewDTO(availabilityNewDTO);

        return createScheduleResponsedto;
    }

    private static AvailabilityNewDTO convertToDTO(AvailabilityNew availabilityNew) {
        AvailabilityNewDTO availabilityNewDTO = new AvailabilityNewDTO();
        availabilityNewDTO.setAvailabilityId(availabilityNew.getAvailabilityId());
        availabilityNewDTO.setScheduleId(availabilityNew.getScheduleId());
        //availabilityNewDTO.setDays(availabilityNew.getDays());
        return availabilityNewDTO;







    }
    public static DeleteScheduleDTO convertEntityToDTO1(Schedule schedule, List availabilityNewList){
        DeleteScheduleDTO deleteSchedule=new DeleteScheduleDTO();
        deleteSchedule.setId(schedule.getScheduleId());
        deleteSchedule.setUserId(schedule.getUserId());
        deleteSchedule.setName(schedule.getName());
        UpdateAvailabilityNewResponseDTO updateAvailabilityNewResponseDTO=new UpdateAvailabilityNewResponseDTO();
        for(int i=0;i<availabilityNewList.size();i++){
            AvailabilityNew availabilityNew=(AvailabilityNew) availabilityNewList.get(i);
            Long day=availabilityNew.getDay();
            List<Slot> ans = catchSlotIdsListAndConvertIntoStartTimeEndTime(availabilityNew.getSlotIds());
            if(day==0){
                List<Slot> sun = ans;
                updateAvailabilityNewResponseDTO.setMon(sun);
            }
            if(day==0){
                List<Slot> mon = ans;
                updateAvailabilityNewResponseDTO.setMon(mon);

            }
            if(day==2){
                List<Slot> tue = ans;
                updateAvailabilityNewResponseDTO.setMon(tue);

            }
            if(day==3){
                List<Slot> wed = ans;
                updateAvailabilityNewResponseDTO.setMon(wed);

            }
            if(day==4){
                List<Slot> thur = ans;
                updateAvailabilityNewResponseDTO.setMon(thur);


            }
            if(day==5){
                List<Slot> fri = ans;
                updateAvailabilityNewResponseDTO.setMon(fri);

            }
            if(day==6){
                List<Slot> sat = ans;
                updateAvailabilityNewResponseDTO.setMon(sat);

            }
        }
        deleteSchedule.setAvailability(updateAvailabilityNewResponseDTO);
        return deleteSchedule;




    }

}
