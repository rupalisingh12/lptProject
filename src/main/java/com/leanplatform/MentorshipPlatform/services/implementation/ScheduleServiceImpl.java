package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.AdminController.AdminAddsDetailsResponse;
import com.leanplatform.MentorshipPlatform.dto.MenteeController.MenteeModified;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabilityNew;
import com.leanplatform.MentorshipPlatform.entities.Schedule;
import com.leanplatform.MentorshipPlatform.entities.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.ScheduleMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityNewRepository;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.repositories.UserRepository;
import com.leanplatform.MentorshipPlatform.services.ScheduleService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
   @Autowired UserRepository userRepository;
   @Autowired AvailabilityNewRepository availabilityNewRepository;
   @Autowired ScheduleRepository scheduleRepository;



  @Override
   public ResponseEntity<CreateScheduleResponse> createSchedules(UUID userId) {
     Schedule schedule=new Schedule();
     schedule.setUserId(userId);
    UserEntity userEntity= userRepository.findByUserId(userId);

    schedule.setName(userEntity.getName());
     Schedule schedule1=scheduleRepository.save(schedule);




   List<AvailabilityNew> availabilityNewList=availabilityNewRepository.findByScheduleId(schedule1.getScheduleId());
   //List<AvailabilityNew>list= (List<AvailabilityNew>) availabilityNewList.get();
     // List<AvailabilityNew> extractedList = availabilityNewList.orElse( Collections.emptyList());


      CreateScheduleResponseDTO createScheduleResponsedto=ScheduleMapper.convertEntityToDTO( schedule,availabilityNewList);



      return new ResponseEntity<>(new CreateScheduleResponse(
                      "1",
                      "Schedule created",createScheduleResponsedto
              ), HttpStatus.CREATED);
  }
  @Override
  public ResponseEntity<GetAllScheduleResponse> getSchedules(UUID userId){
      List<Schedule>schedule1=scheduleRepository.findByUserId( userId);
      if(schedule1!=null) {
          List<CreateScheduleResponseDTO> createScheduleResponseDTOS = new ArrayList<>();
          for (int i = 0; i < schedule1.size(); i++) {
              Schedule schedule = schedule1.get(i);
              List<AvailabilityNew> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
              CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);
              createScheduleResponseDTOS.add(createScheduleResponsedto);

          }
          return new ResponseEntity<>(new GetAllScheduleResponse(
                  "1",
                  "Schedules for the user are ", createScheduleResponseDTOS
          ), HttpStatus.CREATED);
      }else{
          return new ResponseEntity<>(new GetAllScheduleResponse(
                  "0",
                  "Schedule for the user does not exist", null
          ), HttpStatus.NOT_FOUND);

      }

  }
  @Override
 public ResponseEntity<CreateScheduleResponse>getSchdeule(UUID scheduleId,UUID userId) {
      Schedule schedule = scheduleRepository.findByScheduleIdAndUserId(scheduleId, userId);
      if(schedule!=null) {
          List<AvailabilityNew> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
          CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);
          return new ResponseEntity<>(new CreateScheduleResponse(
                  "1",
                  "Schedule is", createScheduleResponsedto
          ), HttpStatus.OK);
      }
      else{
          return new ResponseEntity<>(new CreateScheduleResponse(
                  "0",
                  "No schedule exist for the required scheduleId", null
          ), HttpStatus.NOT_FOUND);

      }

  }
    public ResponseEntity<DeleteSchedule>deleteSchedule(UUID scheduleId, UUID userId){
        Schedule schedule = scheduleRepository.findByScheduleIdAndUserId(scheduleId, userId);
        if(schedule!=null) {
            List<AvailabilityNew> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
            scheduleRepository.delete(schedule);
            for (int i = 0; i < availabilityNewList.size(); i++) {
                availabilityNewRepository.delete(availabilityNewList.get(i));
            }
            DeleteScheduleDTO deleteScheduleDTO = ScheduleMapper.convertEntityToDTO1(schedule, availabilityNewList);
            return new ResponseEntity<>(new DeleteSchedule(
                    "1",
                    "Schedule deleted successfully for scheduleId: "+scheduleId, deleteScheduleDTO
            ), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new DeleteSchedule(
                    "1",
                    "Schedule does not exist for "+scheduleId , null
            ), HttpStatus.OK);

        }


    }







  }



