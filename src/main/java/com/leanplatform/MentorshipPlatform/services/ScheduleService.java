package com.leanplatform.MentorshipPlatform.services;

import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleRequest;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.CreateScheduleResponse;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.DeleteSchedule;
import com.leanplatform.MentorshipPlatform.dto.ScheduleController.GetAllScheduleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ScheduleService {
    ResponseEntity<CreateScheduleResponse> createSchedules(UUID userId, CreateScheduleRequest createScheduleRequest) ;
    ResponseEntity<GetAllScheduleResponse> getSchedules(UUID userId);
    ResponseEntity<CreateScheduleResponse>getSchdeule(UUID userId, UUID scheduleId);
    ResponseEntity<DeleteSchedule>deleteSchedule(UUID scheduleId,UUID userId);
    ResponseEntity<GetAllScheduleResponse>getSchedulesWithAvailabilities( UUID userId);


}