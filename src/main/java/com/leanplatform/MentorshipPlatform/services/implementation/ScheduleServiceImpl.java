package com.leanplatform.MentorshipPlatform.services.implementation;

import com.leanplatform.MentorshipPlatform.dto.ScheduleController.*;
import com.leanplatform.MentorshipPlatform.entities.AvailabiliyFeature.AvailabilityV2;
import com.leanplatform.MentorshipPlatform.entities.EventTypeFeature.EventType;
import com.leanplatform.MentorshipPlatform.entities.MultifunctionEntity.Schedule;
import com.leanplatform.MentorshipPlatform.entities.MentorEntity.UserEntity;
import com.leanplatform.MentorshipPlatform.mappers.ScheduleFunctionalityMapper.ScheduleMapper;
import com.leanplatform.MentorshipPlatform.repositories.AvailabilityFeatureRepository.AvailabilityV2Repository;
import com.leanplatform.MentorshipPlatform.repositories.EventTypeRepository.EventTypesRepository;
import com.leanplatform.MentorshipPlatform.repositories.ScheduleRepository.ScheduleRepository;
import com.leanplatform.MentorshipPlatform.repositories.MentorRepository.UserRepository;
import com.leanplatform.MentorshipPlatform.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AvailabilityV2Repository availabilityNewRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EventTypesRepository eventTypesRepository;


    @Override
    public ResponseEntity<CreateScheduleResponse> createSchedules(UUID userId, CreateScheduleRequest createScheduleRequest) {
        if (userId == null || createScheduleRequest == null || createScheduleRequest.getName() == null) {
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
        Schedule schedule = new Schedule();
        schedule.setUserId(userId);
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            return new ResponseEntity<>(new CreateScheduleResponse(
                    "0",
                    "User does not exist", null
            ), HttpStatus.NOT_FOUND);

        }

        schedule.setName(createScheduleRequest.getName());
        String name1 = schedule.getName();
        Schedule schedule1 = scheduleRepository.save(schedule);


        List<AvailabilityV2> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule1.getScheduleId());
        //List<AvailabilityNew>list= (List<AvailabilityNew>) availabilityNewList.get();
        // List<AvailabilityNew> extractedList = availabilityNewList.orElse( Collections.emptyList());


        CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);


        return new ResponseEntity<>(new CreateScheduleResponse(
                "1",
                "Schedule created", createScheduleResponsedto
        ), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetAllScheduleResponse> getSchedules(UUID userId) {
        if (userId == null) {
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "0",
                            "Invalid request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        List<Schedule> schedule1 = scheduleRepository.findByUserId(userId);
        if (schedule1.isEmpty()) {
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "1",
                            "No schedule Found",
                            null
                    ), HttpStatus.OK);
        } else {
            List<CreateScheduleResponseDTO> createScheduleResponseDTOS = new ArrayList<>();
            for (int i = 0; i < schedule1.size(); i++) {
                Schedule schedule = schedule1.get(i);
                List<AvailabilityV2> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
                CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);
                createScheduleResponseDTOS.add(createScheduleResponsedto);

            }
            return new ResponseEntity<>(new GetAllScheduleResponse(
                    "1",
                    "Schedules for the user are ", createScheduleResponseDTOS
            ), HttpStatus.OK);


        }

    }

    @Override
    public ResponseEntity<CreateScheduleResponse> getSchdeule(UUID scheduleId, UUID userId) {
        if (userId == null || scheduleId == null) {
            return new ResponseEntity<>(new CreateScheduleResponse
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
        Schedule schedule = scheduleRepository.findByScheduleIdAndUserId(scheduleId, userId);
        if (schedule != null) {
            List<AvailabilityV2> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
            CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);
            return new ResponseEntity<>(new CreateScheduleResponse(
                    "1",
                    "Schedule is", createScheduleResponsedto
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CreateScheduleResponse(
                    "0",
                    "No schedule exist for the required scheduleId", null
            ), HttpStatus.NOT_FOUND);

        }

    }

    public ResponseEntity<DeleteSchedule> deleteSchedule(UUID scheduleId, UUID userId) {
        if (userId == null || scheduleId == null) {
            return new ResponseEntity<>(new DeleteSchedule
                    (
                            "0",
                            "Invalid Request",
                            null
                    ), HttpStatus.BAD_REQUEST);


        }
        Schedule schedule = scheduleRepository.findByScheduleIdAndUserId(scheduleId, userId);
        if (schedule != null) {
            List<AvailabilityV2> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());

            scheduleRepository.delete(schedule);
            if (!availabilityNewList.isEmpty()) {
                for (int i = 0; i < availabilityNewList.size(); i++) {
                    availabilityNewRepository.delete(availabilityNewList.get(i));
                }
            }
            List<EventType> eventTypesList = eventTypesRepository.findByScheduleId(scheduleId);

            if (eventTypesList.size()>0) {
                for (int i = 0; i < eventTypesList.size(); i++) {
                    EventType eventType = eventTypesList.get(i);
                    eventType.setScheduleId(null);
                    eventTypesRepository.save(eventType);


                }
            }
            DeleteScheduleDTO deleteScheduleDTO = ScheduleMapper.convertEntityToDTO1(schedule, availabilityNewList);
            return new ResponseEntity<>(new DeleteSchedule(
                    "1",
                    "Schedule deleted successfully for scheduleId: " + scheduleId, deleteScheduleDTO
            ), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DeleteSchedule(
                    "0",
                    "Schedule does not exist for " + scheduleId, null
            ), HttpStatus.NOT_FOUND);

        }


    }

    @Override
    public ResponseEntity<GetAllScheduleResponse> getSchedulesWithAvailabilities(UUID userId) {
        if (userId == null) {
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "0",
                            "Invalid request",
                            null
                    ), HttpStatus.BAD_REQUEST);
        }
        List<Schedule> schedule1 = scheduleRepository.findByUserId(userId);
        if (schedule1.isEmpty()) {
            return new ResponseEntity<>(new GetAllScheduleResponse
                    (
                            "1",
                            "No schedule Found For this user",
                            null
                    ), HttpStatus.OK);
        } else {
            List<CreateScheduleResponseDTO> createScheduleResponseDTOS = new ArrayList<>();
            for (int i = 0; i < schedule1.size(); i++) {
                Schedule schedule = schedule1.get(i);
                List<AvailabilityV2> availabilityNewList = availabilityNewRepository.findByScheduleId(schedule.getScheduleId());
                if (!availabilityNewList.isEmpty()) {
                    CreateScheduleResponseDTO createScheduleResponsedto = ScheduleMapper.convertEntityToDTO(schedule, availabilityNewList);
                    createScheduleResponseDTOS.add(createScheduleResponsedto);
                } else {
                    //do not add this in the list
                }

            }
            return new ResponseEntity<>(new GetAllScheduleResponse(
                    "1",
                    "Schedules for the user are ", createScheduleResponseDTOS
            ), HttpStatus.OK);


        }
    }
}











