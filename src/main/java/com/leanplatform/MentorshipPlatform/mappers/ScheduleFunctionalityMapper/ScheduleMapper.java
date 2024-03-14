package com.leanplatform.MentorshipPlatform.mappers.ScheduleFunctionalityMapper;

import com.leanplatform.MentorshipPlatform.dto.AvailabilityV2Controller.UpdateAvailabilityNewResponseDTO;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Schedule;
import com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.Slot;

import java.util.List;

import static com.leanplatform.MentorshipPlatform.mappers.AvailabilityFeatureMapper.AvailabilityV2Mapper.catchSlotIdsListAndConvertIntoStartTimeEndTime;

public class ScheduleMapper {
    public static CreateScheduleResponseDTO convertEntityToDTO(Schedule schedule, List<AvailabilityV2>availabilityNewList) {
        CreateScheduleResponseDTO createScheduleResponsedto = new CreateScheduleResponseDTO();
        createScheduleResponsedto.setScheduleId(schedule.getScheduleId());
        createScheduleResponsedto.setUserId(schedule.getUserId());
        createScheduleResponsedto.setName(schedule.getName());
        UpdateAvailabilityNewResponseDTO  updateAvailabilityNewResponseDTO=new UpdateAvailabilityNewResponseDTO();
//        List<AvailabilityNew> availabilityNewList1 = new ArrayList<>();
//        AvailabilityNewDTO availabilityNewDTO = null;
        for (int i = 0; i < availabilityNewList.size(); i++) {

                AvailabilityV2 availabilityNew = availabilityNewList.get(i);
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

    private static AvailabilityV2DTO convertToDTO(AvailabilityV2 availabilityNew) {
        AvailabilityV2DTO availabilityNewDTO = new AvailabilityV2DTO();
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
            AvailabilityV2 availabilityNew=(AvailabilityV2) availabilityNewList.get(i);
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
